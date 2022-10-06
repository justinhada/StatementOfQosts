package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Benutzername;
import de.justinharder.soq.domain.model.attribute.EMailAdresse;
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
	private EMailAdresse eMailAdresse;

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
	@JoinColumn(name = "BenutzerID", nullable = false)
	private Benutzer benutzer;

	public static Validation<Meldungen, Login> aus(
		EMailAdresse eMailAdresse,
		Benutzername benutzername,
		Salt salt,
		Passwort passwort,
		Benutzer benutzer)
	{
		return Validation.combine(
				validiere(eMailAdresse, Meldung.E_MAIL_ADRESSE_LEER),
				validiere(benutzername, Meldung.BENUTZERNAME_LEER),
				validiere(salt, Meldung.SALT),
				validiere(passwort, Meldung.PASSWORT_LEER),
				validiere(benutzer, Meldung.BENUTZER_LEER))
			.ap(Login::new)
			.bimap(Meldungen::ausSeq, Function.identity());
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("E-Mail-Adresse", eMailAdresse)
			.add("Benutzername", benutzername)
			.add("Salt", salt)
			.add("Passwort", passwort)
			.add("Benutzer", benutzer)
			.toString();
	}
}
