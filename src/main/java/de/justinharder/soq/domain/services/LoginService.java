package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.attribute.Benutzername;
import de.justinharder.soq.domain.model.attribute.Passwort;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.repository.LoginRepository;
import de.justinharder.soq.domain.services.dto.AngemeldeterBenutzer;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static java.util.function.Predicate.not;

@Dependent
public class LoginService
{
	@NonNull
	private final LoginRepository loginRepository;

	@Inject
	public LoginService(@NonNull LoginRepository loginRepository)
	{
		this.loginRepository = loginRepository;
	}

	public AngemeldeterBenutzer login(@NonNull AngemeldeterBenutzer angemeldeterBenutzer)
	{
		return Validation.combine(
				Benutzername.aus(angemeldeterBenutzer.getBenutzername()),
				Passwort.validierePasswort(angemeldeterBenutzer.getPasswort()))
			.ap((benutzername, passwort) -> loginRepository.finde(benutzername))
			.mapError(Meldungen::aus)
			.flatMap(login -> login.toValidation(Meldungen.aus(Meldung.BENUTZERNAME_EXISTIERT_NICHT)))
			.filter(not(login -> Passwort.aus(
					login.getSalt(),
					Passwort.validierePasswort(angemeldeterBenutzer.getPasswort()).get()).get()
				.equals(login.getPasswort())))
			.getOrElse(() -> Validation.invalid(Meldungen.aus(Meldung.PASSWORT_FALSCH)))
			.fold(angemeldeterBenutzer::fuegeMeldungenHinzu,
				login -> angemeldeterBenutzer.fuegeMeldungHinzu(Meldung.LOGIN_ERFOLGREICH));
	}
}
