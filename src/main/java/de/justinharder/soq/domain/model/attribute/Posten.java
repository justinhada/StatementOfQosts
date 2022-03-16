package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Posten extends WertObjekt<String>
{
	@Serial
	private static final long serialVersionUID = 253564988800012415L;

	@NonNull
	@Column(name = "Posten", nullable = false)
	private String wert;

	public static Validation<Meldung, Posten> aus(String wert)
	{
		return validiereString(wert, Schluessel.POSTEN, "Der Posten darf nicht leer sein!")
			.map(Posten::new);
	}
}
