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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Firma extends WertObjekt<String>
{
	@Serial
	private static final long serialVersionUID = -5444340705592920809L;

	@Column(name = "Firma")
	private String wert;

	public static Validation<Meldungen, Firma> aus(String wert)
	{
		return validiereString(wert, Meldung.FIRMA_LEER)
			.map(Firma::new);
	}
}
