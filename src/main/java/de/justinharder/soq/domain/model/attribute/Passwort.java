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
import java.util.function.IntPredicate;

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
				validierePasswort(wert),
				validiere(salt, Meldung.SALT))
			.ap(Passwort::hash)
			.mapError(Meldungen::ausSeq)
			.flatMap(passwortTry -> passwortTry.toValidation(() -> Meldungen.aus(Meldung.PASSWORT_HASH)));
	}

	public static Validation<Meldung, String> validierePasswort(String passwort)
	{
		return validiereString(passwort, Meldung.PASSWORT_LEER)
			.flatMap(Passwort::pruefeGrossbuchstabe)
			.flatMap(Passwort::pruefeKleinbuchstabe)
			.flatMap(Passwort::pruefeZahl)
			.flatMap(Passwort::pruefeSonderzeichen)
			.flatMap(Passwort::pruefeLaenge);
	}

	private static Validation<Meldung, String> pruefeGrossbuchstabe(String passwort)
	{
		return pruefeAuf(passwort, Character::isUpperCase);
	}

	private static Validation<Meldung, String> pruefeKleinbuchstabe(String passwort)
	{
		return pruefeAuf(passwort, Character::isLowerCase);
	}

	private static Validation<Meldung, String> pruefeZahl(String passwort)
	{
		return pruefeAuf(passwort, Character::isDigit);
	}

	private static Validation<Meldung, String> pruefeSonderzeichen(String passwort)
	{
		return pruefeAuf(passwort, character -> !Character.isLetterOrDigit(character));
	}

	private static Validation<Meldung, String> pruefeAuf(String passwort, IntPredicate pruefung)
	{
		return passwort.chars().anyMatch(pruefung)
			? Validation.valid(passwort)
			: Validation.invalid(Meldung.PASSWORT_UNGUELTIG);
	}

	private static Validation<Meldung, String> pruefeLaenge(String passwort)
	{
		return passwort.length() < 12 ? Validation.invalid(Meldung.PASSWORT_UNGUELTIG) : Validation.valid(passwort);
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
