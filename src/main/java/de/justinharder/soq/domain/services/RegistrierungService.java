package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.Login;
import de.justinharder.soq.domain.model.Person;
import de.justinharder.soq.domain.model.attribute.Benutzername;
import de.justinharder.soq.domain.model.attribute.EmailAdresse;
import de.justinharder.soq.domain.model.attribute.Passwort;
import de.justinharder.soq.domain.model.attribute.Salt;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.repository.LoginRepository;
import de.justinharder.soq.domain.repository.PersonRepository;
import de.justinharder.soq.domain.services.dto.NeuePerson;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class RegistrierungService
{
	@NonNull
	private final PersonRepository personRepository;

	@NonNull
	private final LoginRepository loginRepository;

	@Inject
	public RegistrierungService(@NonNull PersonRepository personRepository, @NonNull LoginRepository loginRepository)
	{
		this.personRepository = personRepository;
		this.loginRepository = loginRepository;
	}

	public NeuePerson registriere(@NonNull NeuePerson neuePerson)
	{
		Salt salt = Salt.random();
		Validation<Meldungen, Passwort> passwort = Passwort.aus(salt, neuePerson.getPasswort());
		Validation<Meldungen, Passwort> passwortWiederholen = Passwort.aus(salt, neuePerson.getPasswortWiederholen());

		if (passwort.isInvalid())
		{
			neuePerson.fuegeMeldungenHinzu(passwort.getError());
		}

		if (passwortWiederholen.isInvalid())
		{
			neuePerson.fuegeMeldungenHinzu(passwortWiederholen.getError());
		}

		Validation<Meldungen, Person> person = Person.aus(neuePerson.getNachname(), neuePerson.getVorname());

		if (person.isInvalid())
		{
			neuePerson.fuegeMeldungenHinzu(person.getError());
		}

		var emailAdresse = EmailAdresse.aus(neuePerson.getEmailadresse());

		if (emailAdresse.isInvalid())
		{
			neuePerson.fuegeMeldungHinzu(emailAdresse.getError());
		}

		var benutzername = Benutzername.aus(neuePerson.getBenutzername());

		if (benutzername.isInvalid())
		{
			neuePerson.fuegeMeldungHinzu(benutzername.getError());
		}

		if (neuePerson.hatMeldungen())
		{
			return neuePerson;
		}

		Passwort validesPasswort = passwort.get();
		Passwort validesPasswortWiederholen = passwortWiederholen.get();

		if (validesPasswort.equals(validesPasswortWiederholen))
		{
			neuePerson.fuegeMeldungHinzu(Meldung.PASSWORT_UNGLEICH);
		}

		if (loginRepository.finde(benutzername.get()).isDefined())
		{
			return neuePerson.fuegeMeldungHinzu(Meldung.BENUTZERNAME_VERGEBEN);
		}

		var login = Login.aus(emailAdresse.get(), benutzername.get(), salt, validesPasswort, person.get());

		if (login.isInvalid())
		{
			return neuePerson.fuegeMeldungenHinzu(login.getError());
		}

		personRepository.speichere(person.get());
		loginRepository.speichere(login.get());

		return new NeuePerson().fuegeMeldungHinzu(Meldung.PERSON_ERSTELLT);
	}
}
