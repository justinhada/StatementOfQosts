package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.attribute.IBAN;
import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BankRepository;
import de.justinharder.soq.domain.repository.BankverbindungRepository;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.services.dto.GespeicherteBankverbindung;
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
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final BankRepository bankRepository;

	@NonNull
	private final BankverbindungMapping bankverbindungMapping;

	@Inject
	public BankverbindungService(
		@NonNull BankverbindungRepository bankverbindungRepository,
		@NonNull BenutzerRepository benutzerRepository,
		@NonNull BankRepository bankRepository,
		@NonNull BankverbindungMapping bankverbindungMapping)
	{
		this.bankverbindungRepository = bankverbindungRepository;
		this.benutzerRepository = benutzerRepository;
		this.bankRepository = bankRepository;
		this.bankverbindungMapping = bankverbindungMapping;
	}

	public List<GespeicherteBankverbindung> findeAlle()
	{
		return bankverbindungRepository.findeAlle().stream()
			.map(bankverbindungMapping::mappe)
			.toList();
	}

	@Transactional
	public NeueBankverbindung erstelle(NeueBankverbindung neueBankverbindung)
	{
		return Validation.combine(
				IBAN.aus(neueBankverbindung.getIban())
					.filter(not(bankverbindungRepository::istVorhanden))
					.getOrElse(Validation.invalid(Meldungen.aus(Meldung.IBAN_EXISTIERT_BEREITS))),
				ID.aus(neueBankverbindung.getBenutzerId(), Schluessel.BENUTZER)
					.map(benutzerRepository::finde)
					.flatMap(b -> b.toValidation(Meldungen.aus(Meldung.BENUTZER_EXISTIERT_NICHT))),
				ID.aus(neueBankverbindung.getBankId(), Schluessel.BANK)
					.map(bankRepository::finde)
					.flatMap(ba -> ba.toValidation(Meldungen.aus(Meldung.BANK_EXISTIERT_NICHT))))
			.ap(Bankverbindung::aus)
			.mapError(Meldungen::aus)
			.flatMap(Function.identity())
			.fold(neueBankverbindung::fuegeMeldungenHinzu, bankverbindung -> {
				bankverbindungRepository.speichere(bankverbindung);
				return new NeueBankverbindung().fuegeMeldungHinzu(Meldung.BANKVERBINDUNG_ERSTELLT);
			});
	}
}
