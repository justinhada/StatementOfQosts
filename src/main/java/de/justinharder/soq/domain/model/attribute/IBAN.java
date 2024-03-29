package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;
import org.apache.commons.validator.routines.IBANValidator;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class IBAN extends WertObjekt<String>
{
	@Serial
	private static final long serialVersionUID = 6390864789388990741L;

	@NonNull
	@Column(name = "IBAN", nullable = false)
	private String wert;

	public static Validation<Meldungen, IBAN> aus(String wert)
	{
		return validiereString(wert, Meldung.IBAN_LEER)
			.map(s -> s.replace(" ", ""))
			.filter(IBANValidator.DEFAULT_IBAN_VALIDATOR::isValid)
			.getOrElse(Validation.invalid(Meldungen.aus(Meldung.IBAN_UNGUELTIG)))
			.map(IBAN::new);
	}

	@Override
	public String toString()
	{
		var ibanBuffer = new StringBuilder(wert);
		var length = ibanBuffer.length();

		for (int i = 0; i < length / 4; i++) {
			ibanBuffer.insert((i + 1) * 4 + i, " ");
		}

		return ibanBuffer.toString().trim();
	}
}
