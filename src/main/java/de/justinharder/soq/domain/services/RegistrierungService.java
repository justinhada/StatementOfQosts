package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.Login;
import de.justinharder.soq.domain.model.attribute.*;
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

		//		Validation.combine(
		//				Nachname.aus(neuerBenutzer.getNachname()),
		//				Vorname.aus(neuerBenutzer.getVorname()))
		//			.ap(Benutzer::aus)
		//			.mapError(Meldungen::aus)
		//			.flatMap(Function.identity())
		//			.fold(neuerBenutzer::fuegeMeldungenHinzu, benutzerRepository::speichere);

		var nachname = Nachname.aus(neuerBenutzer.getNachname());
		if (nachname.isInvalid())
		{
			neuerBenutzer.fuegeMeldungenHinzu(nachname.getError());
		}

		var vorname = Vorname.aus(neuerBenutzer.getVorname());
		if (vorname.isInvalid())
		{
			neuerBenutzer.fuegeMeldungenHinzu(vorname.getError());
		}

		var emailAdresse = EMailAdresse.aus(neuerBenutzer.getEmailadresse());
		if (emailAdresse.isInvalid())
		{
			neuerBenutzer.fuegeMeldungenHinzu(emailAdresse.getError());
		}

		var benutzername = Benutzername.aus(neuerBenutzer.getBenutzername());
		if (benutzername.isInvalid())
		{
			neuerBenutzer.fuegeMeldungenHinzu(benutzername.getError());
		}

		if (neuerBenutzer.hatMeldungen())
		{
			return neuerBenutzer;
		}

		var benutzer = Benutzer.aus(nachname.get(), vorname.get());
		if (benutzer.isInvalid())
		{
			neuerBenutzer.fuegeMeldungenHinzu(benutzer.getError());
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
