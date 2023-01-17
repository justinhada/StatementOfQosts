package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.attribute.IBAN;
import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BankRepository;
import de.justinharder.soq.domain.repository.BankverbindungRepository;
import de.justinharder.soq.domain.repository.KontoinhaberRepository;
import de.justinharder.soq.domain.services.dto.GespeicherteBankverbindung;
import de.justinharder.soq.domain.services.dto.GespeicherterAuftraggeber;
import de.justinharder.soq.domain.services.dto.GespeicherterZahlungsbeteiligter;
import de.justinharder.soq.domain.services.dto.NeueBankverbindung;
import de.justinharder.soq.domain.services.mapping.BankverbindungMapping;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;

import static java.util.function.Predicate.not;

@Dependent
public class BankverbindungService
{
	@NonNull
	private final BankverbindungRepository bankverbindungRepository;

	@NonNull
	private final BankRepository bankRepository;

	@NonNull
	private final KontoinhaberRepository kontoinhaberRepository;

	@NonNull
	private final BankverbindungMapping bankverbindungMapping;

	@Inject
	public BankverbindungService(
		@NonNull BankverbindungRepository bankverbindungRepository,
		@NonNull BankRepository bankRepository,
		@NonNull KontoinhaberRepository kontoinhaberRepository,
		@NonNull BankverbindungMapping bankverbindungMapping)
	{
		this.bankverbindungRepository = bankverbindungRepository;
		this.kontoinhaberRepository = kontoinhaberRepository;
		this.bankRepository = bankRepository;
		this.bankverbindungMapping = bankverbindungMapping;
	}

	public List<GespeicherteBankverbindung> findeAlle()
	{
		return bankverbindungRepository.findeAlle().stream()
			.map(bankverbindungMapping::mappe)
			.toList();
	}

	public List<GespeicherterAuftraggeber> findeAlleAuftraggeber()
	{
		return bankverbindungRepository.findeAlle().stream()
			.map(bankverbindung -> bankverbindungMapping.mappeZuAuftraggeber(
				bankverbindung,
				kontoinhaberRepository.findeAlle(bankverbindung)))
			.toList();
	}

	public List<GespeicherterZahlungsbeteiligter> findeAlleZahlungsbeteiligten()
	{
		return bankverbindungRepository.findeAlle().stream()
			.map(bankverbindung -> bankverbindungMapping.mappeZuZahlungsbeteiligter(
				bankverbindung,
				kontoinhaberRepository.findeAlle(bankverbindung)))
			.toList();
	}

	public GespeicherteBankverbindung finde(@NonNull String id)
	{
		return ID.aus(id, Schluessel.BANKVERBINDUNG)
			.map(bankverbindungRepository::finde)
			.flatMap(bankverbindung -> bankverbindung
				.toValidation(Meldungen.aus(Meldung.BANKVERBINDUNG_EXISTIERT_NICHT)))
			.fold(new GespeicherteBankverbindung()::fuegeMeldungenHinzu, bankverbindungMapping::mappe);
	}

	@Transactional
	public NeueBankverbindung erstelle(@NonNull NeueBankverbindung neueBankverbindung)
	{
		return Validation.combine(
				IBAN.aus(neueBankverbindung.getIban())
					.filter(not(bankverbindungRepository::istVorhanden))
					.getOrElse(Validation.invalid(Meldungen.aus(Meldung.IBAN_EXISTIERT_BEREITS))),
				ID.aus(neueBankverbindung.getBankId(), Schluessel.BANK)
					.map(bankRepository::finde)
					.flatMap(bank -> bank.toValidation(Meldungen.aus(Meldung.BANK_EXISTIERT_NICHT))))
			.ap(Bankverbindung::aus)
			.mapError(Meldungen::aus)
			.flatMap(Function.identity())
			.fold(neueBankverbindung::fuegeMeldungenHinzu, bankverbindung -> {
				bankverbindungRepository.speichere(bankverbindung);
				return new NeueBankverbindung(bankverbindung.getId().getWert().toString()).fuegeMeldungHinzu(
					Meldung.BANKVERBINDUNG_ERSTELLT);
			});
	}
}
