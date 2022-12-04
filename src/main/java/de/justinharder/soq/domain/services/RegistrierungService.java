package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.Login;
import de.justinharder.soq.domain.model.attribute.*;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.repository.LoginRepository;
import de.justinharder.soq.domain.services.dto.NeuerBenutzer;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.function.Function;

import static java.util.function.Predicate.not;

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
		return Validation.combine(
				EMailAdresse.aus(neuerBenutzer.getEmailadresse()),
				Benutzername.aus(neuerBenutzer.getBenutzername()),
				Validation.valid(salt),
				Passwort.aus(salt, neuerBenutzer.getPasswort()),
				Validation.combine(
						Nachname.aus(neuerBenutzer.getNachname()),
						Vorname.aus(neuerBenutzer.getVorname()))
					.ap(Benutzer::aus)
					.mapError(Meldungen::aus)
					.flatMap(Function.identity()))
			.ap(Login::aus)
			.mapError(Meldungen::aus)
			.flatMap(Function.identity())
			.filter(not(login -> loginRepository.finde(login.getBenutzername()).isDefined()))
			.getOrElse(() -> Validation.invalid(Meldungen.aus(Meldung.BENUTZERNAME_EXISTIERT_BEREITS)))
			.fold(neuerBenutzer::fuegeMeldungenHinzu, login -> {
				benutzerRepository.speichere(login.getBenutzer());
				loginRepository.speichere(login);
				return new NeuerBenutzer().fuegeMeldungHinzu(Meldung.BENUTZER_ERSTELLT);
			});
	}
}
