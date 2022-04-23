package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.Login;
import de.justinharder.soq.domain.model.attribute.Benutzername;
import de.justinharder.soq.domain.model.attribute.EMailAdresse;
import de.justinharder.soq.domain.model.attribute.Passwort;
import de.justinharder.soq.domain.model.attribute.Salt;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.repository.LoginRepository;
import de.justinharder.soq.domain.services.dto.NeuerBenutzer;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Dependent
public class RegistrierungService
{
	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final LoginRepository loginRepository;

	@Inject
	public RegistrierungService(
		@NonNull BenutzerRepository benutzerRepository,
		@NonNull LoginRepository loginRepository)
	{
		this.benutzerRepository = benutzerRepository;
		this.loginRepository = loginRepository;
	}

	@Transactional
	public NeuerBenutzer registriere(@NonNull NeuerBenutzer neuerBenutzer)
	{
		var salt = Salt.random();
		var passwort = Passwort.aus(salt, neuerBenutzer.getPasswort());
		if (passwort.isInvalid())
		{
			neuerBenutzer.fuegeMeldungenHinzu(passwort.getError());
		}

		var benutzer = Benutzer.aus(neuerBenutzer.getNachname(), neuerBenutzer.getVorname());
		if (benutzer.isInvalid())
		{
			neuerBenutzer.fuegeMeldungenHinzu(benutzer.getError());
		}

		var emailAdresse = EMailAdresse.aus(neuerBenutzer.getEmailadresse());
		if (emailAdresse.isInvalid())
		{
			neuerBenutzer.fuegeMeldungHinzu(emailAdresse.getError());
		}

		var benutzername = Benutzername.aus(neuerBenutzer.getBenutzername());
		if (benutzername.isInvalid())
		{
			neuerBenutzer.fuegeMeldungHinzu(benutzername.getError());
		}

		if (neuerBenutzer.hatMeldungen())
		{
			return neuerBenutzer;
		}

		if (loginRepository.finde(benutzername.get()).isDefined())
		{
			return neuerBenutzer.fuegeMeldungHinzu(Meldung.BENUTZERNAME_VERGEBEN);
		}

		var login = Login.aus(emailAdresse.get(), benutzername.get(), salt, passwort.get(), benutzer.get());
		if (login.isInvalid())
		{
			return neuerBenutzer.fuegeMeldungenHinzu(login.getError());
		}

		benutzerRepository.speichere(benutzer.get());
		loginRepository.speichere(login.get());
		return new NeuerBenutzer().fuegeMeldungHinzu(Meldung.BENUTZER_ERSTELLT);
	}
}
