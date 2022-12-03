package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.attribute.IBAN;
import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BankRepository;
import de.justinharder.soq.domain.repository.BankverbindungRepository;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.services.dto.GespeicherteBankverbindung;
import de.justinharder.soq.domain.services.dto.NeueBankverbindung;
import de.justinharder.soq.domain.services.mapping.BankverbindungMapping;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

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
		var iban = IBAN.aus(neueBankverbindung.getIban());
		var benutzerId = ID.aus(neueBankverbindung.getBenutzerId(), Schluessel.BENUTZER);
		var bankId = ID.aus(neueBankverbindung.getBankId(), Schluessel.BANK);

		return null;
	}
}
