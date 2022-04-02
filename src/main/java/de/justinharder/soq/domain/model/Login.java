package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Benutzername;
import de.justinharder.soq.domain.model.attribute.EmailAdresse;
import de.justinharder.soq.domain.model.attribute.Passwort;
import de.justinharder.soq.domain.model.attribute.Salt;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.util.function.Function;

@Entity
@Getter
@Table(name = "Login")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Login extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -2791077622025263428L;

	@NonNull
	@Embedded
	private EmailAdresse emailAdresse;

	@NonNull
	@Embedded
	private Benutzername benutzername;

	@NonNull
	@Embedded
	private Salt salt;

	@NonNull
	@Embedded
	private Passwort passwort;

	@NonNull
	@OneToOne(optional = false)
	@JoinColumn(name = "PersonID", nullable = false)
	private Person person;

	public static Validation<Meldungen, Login> aus(
		EmailAdresse emailAdresse,
		Benutzername benutzername,
		Salt salt,
		Passwort passwort,
		Person person)
	{
		return Validation.combine(
				validiere(emailAdresse, Meldung.E_MAIL_ADRESSE_LEER),
				validiere(benutzername, Meldung.BENUTZERNAME_LEER),
				validiere(salt, Meldung.SALT),
				validiere(passwort, Meldung.PASSWORT_LEER),
				validiere(person, Meldung.PERSON_LEER))
			.ap(Login::new)
			.bimap(Meldungen::ausSeq, Function.identity());
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("E-Mail-Adresse", emailAdresse)
			.add("Benutzername", benutzername)
			.add("Salt", salt)
			.add("Passwort", passwort)
			.add("Person", person)
			.toString();
	}
}
