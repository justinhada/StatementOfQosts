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
public class Bezeichnung extends WertObjekt<String>
{
	@Serial
	private static final long serialVersionUID = 3864063299218421073L;

	@NonNull
	@Column(name = "Bezeichnung", nullable = false)
	private String wert;

	public static Validation<Meldung, Bezeichnung> aus(String wert)
	{
		return validiereString(wert, Meldung.BEZEICHNUNG)
			.map(Bezeichnung::new);
	}
}
