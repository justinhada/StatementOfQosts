package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.Bank;
import de.justinharder.soq.domain.model.attribute.BIC;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BankRepository;
import de.justinharder.soq.domain.services.dto.GeloeschteBank;
import de.justinharder.soq.domain.services.dto.GespeicherteBank;
import de.justinharder.soq.domain.services.dto.NeueBank;
import de.justinharder.soq.domain.services.mapping.BankMapping;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;

import static java.util.function.Predicate.not;

@Dependent
public class BankService
{
	@NonNull
	private final BankRepository bankRepository;

	@NonNull
	private final BankMapping bankMapping;

	@Inject
	public BankService(@NonNull BankRepository bankRepository, @NonNull BankMapping bankMapping)
	{
		this.bankRepository = bankRepository;
		this.bankMapping = bankMapping;
	}

	public List<GespeicherteBank> findeAlle()
	{
		return bankRepository.findeAlle().stream()
			.map(bankMapping::mappe)
			.toList();
	}

	public GespeicherteBank finde(@NonNull String id)
	{
		return ID.aus(id, Schluessel.BANK)
			.map(bankRepository::finde)
			.flatMap(bank -> bank.toValidation(Meldungen.aus(Meldung.BANK_EXISTIERT_NICHT)))
			.fold(meldungen -> new GespeicherteBank().fuegeMeldungenHinzu(meldungen), bankMapping::mappe);
	}

	@Transactional
	public NeueBank erstelle(@NonNull NeueBank neueBank)
	{
		return Validation.combine(
				Bezeichnung.aus(neueBank.getBezeichnung())
					.filter(not(bankRepository::istVorhanden))
					.getOrElse(Validation.invalid(Meldungen.aus(Meldung.BEZEICHNUNG_EXISTIERT_BEREITS))),
				BIC.aus(neueBank.getBic())
					.filter(not(bankRepository::istVorhanden))
					.getOrElse(Validation.invalid(Meldungen.aus(Meldung.BIC_EXISTIERT_BEREITS))))
			.ap(Bank::aus)
			.mapError(Meldungen::aus)
			.flatMap(Function.identity())
			.fold(neueBank::fuegeMeldungenHinzu, bank -> {
				bankRepository.speichere(bank);
				return new NeueBank().fuegeMeldungHinzu(Meldung.BANK_ERSTELLT);
			});
	}

	@Transactional
	public GespeicherteBank aktualisiere(@NonNull GespeicherteBank gespeicherteBank)
	{
		return ID.aus(gespeicherteBank.getId(), Schluessel.ALLGEMEIN)
			.map(bankRepository::finde)
			.flatMap(bank -> bank.toValidation(Meldungen.aus(Meldung.BANK_EXISTIERT_NICHT_ALLGEMEIN)))
			.fold(gespeicherteBank::fuegeMeldungenHinzu, bank -> Validation.combine(
					Bezeichnung.aus(gespeicherteBank.getBezeichnung()),
					Validation.valid(bank.getBic()))
				.ap(Bank::aus)
				.mapError(Meldungen::aus)
				.flatMap(Function.identity())
				.fold(gespeicherteBank::fuegeMeldungenHinzu, neueBank -> {
					var bezeichnung = neueBank.getBezeichnung();
					if (!bank.getBezeichnung().equals(bezeichnung) && bankRepository.istVorhanden(bezeichnung))
					{
						return gespeicherteBank.fuegeMeldungHinzu(Meldung.BEZEICHNUNG_EXISTIERT_BEREITS);
					}
					bankRepository.speichere(bank.setBezeichnung(bezeichnung));
					return bankMapping.mappe(bank).fuegeMeldungHinzu(Meldung.BANK_AKTUALISIERT);
				}));
	}

	@Transactional
	public GeloeschteBank loesche(@NonNull String id)
	{
		return ID.aus(id, Schluessel.BANK)
			.map(bankRepository::finde)
			.flatMap(bank -> bank.toValidation(Meldungen.aus(Meldung.BANK_EXISTIERT_NICHT)))
			.fold(meldungen -> new GeloeschteBank().fuegeMeldungenHinzu(meldungen), bank -> {
				bankRepository.loesche(bank);
				return new GeloeschteBank().fuegeMeldungHinzu(Meldung.BANK_GELOESCHT);
			});
	}
}
