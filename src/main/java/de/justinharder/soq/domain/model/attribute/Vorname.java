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
public class Vorname extends WertObjekt<String>
{
	@Serial
	private static final long serialVersionUID = -2969529612633353459L;

	@NonNull
	@Column(name = "Vorname", nullable = false)
	private String wert;

	public static Validation<Meldung, Vorname> aus(String wert)
	{
		return validiereString(wert, Schluessel.VORNAME, "Der Vorname darf nicht leer sein!")
			.map(Vorname::new);
	}
}
