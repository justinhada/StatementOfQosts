package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import lombok.*;

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

	public static Validation<Meldung, IBAN> aus(String wert)
	{
		// TODO: IBAN richtig validieren!
		return validiereString(wert, Meldung.IBAN)
			.map(IBAN::new);
	}
}
