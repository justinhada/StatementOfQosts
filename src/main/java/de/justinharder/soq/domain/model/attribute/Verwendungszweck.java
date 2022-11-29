package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Verwendungszweck extends WertObjekt<String>
{
	@Serial
	private static final long serialVersionUID = 6979677265045740322L;

	@NonNull
	@Column(name = "Verwendungszweck", nullable = false)
	private String wert;

	public static Validation<Meldungen, Verwendungszweck> aus(String wert)
	{
		return validiereString(wert, Meldung.VERWENDUNGSZWECK)
			.map(Verwendungszweck::new);
	}
}
