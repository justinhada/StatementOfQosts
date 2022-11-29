package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.attribute.Benutzername;
import de.justinharder.soq.domain.model.attribute.Passwort;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.repository.LoginRepository;
import de.justinharder.soq.domain.services.dto.AngemeldeterBenutzer;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

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
		var benutzername = Benutzername.aus(angemeldeterBenutzer.getBenutzername());
		if (benutzername.isInvalid())
		{
			angemeldeterBenutzer.fuegeMeldungenHinzu(benutzername.getError());
		}

		var passwort = Passwort.validierePasswort(angemeldeterBenutzer.getPasswort());
		if (passwort.isInvalid())
		{
			angemeldeterBenutzer.fuegeMeldungenHinzu(passwort.getError());
		}

		if (angemeldeterBenutzer.hatMeldungen())
		{
			return angemeldeterBenutzer;
		}

		var login = loginRepository.finde(benutzername.get());
		if (login.isEmpty())
		{
			return angemeldeterBenutzer.fuegeMeldungHinzu(Meldung.BENUTZERNAME_EXISTIERT_NICHT);
		}

		if (!Passwort.aus(login.get().getSalt(), passwort.get()).get().equals(login.get().getPasswort()))
		{
			return angemeldeterBenutzer.fuegeMeldungHinzu(Meldung.PASSWORT_FALSCH);
		}

		return angemeldeterBenutzer.fuegeMeldungHinzu(Meldung.LOGIN_ERFOLGREICH);
	}
}
