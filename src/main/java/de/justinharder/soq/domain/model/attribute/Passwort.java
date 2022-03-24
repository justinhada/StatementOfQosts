package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import lombok.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.util.Base64;
import java.util.function.Function;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Passwort extends WertObjekt<String>
{
	@Serial
	private static final long serialVersionUID = -1755430787167587386L;

	@NonNull
	@Column(name = "Passwort", nullable = false)
	private String wert;

	public static Validation<Meldungen, Passwort> aus(Salt salt, String wert)
	{
		return Validation.combine(
				validiereString(wert, Meldung.PASSWORT_LEER),
				validiere(salt, Meldung.SALT))
			.ap(Passwort::hash)
			.bimap(Meldungen::ausSeq, Function.identity())
			.flatMap(passwortTry -> passwortTry.toValidation(() -> Meldungen.aus(Meldung.PASSWORT_HASH)));
	}

	private static Try<Passwort> hash(String passwort, Salt salt)
	{
		return Try.of(() -> new Passwort(Base64.getEncoder().encodeToString(SecretKeyFactory
			.getInstance("PBKDF2WithHmacSHA1")
			.generateSecret(
				new PBEKeySpec(passwort.toCharArray(), Base64.getDecoder().decode(salt.getWert()), 65536, 128))
			.getEncoded())));
	}
}
