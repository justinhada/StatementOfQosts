package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.Bank;
import de.justinharder.soq.domain.model.attribute.BIC;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.repository.BankRepository;
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

}
