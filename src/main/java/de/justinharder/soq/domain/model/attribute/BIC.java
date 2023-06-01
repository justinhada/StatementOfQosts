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
public class BIC extends WertObjekt<String>
{
	@Serial
	private static final long serialVersionUID = 5820278504329030L;

	@NonNull
	@Column(name = "BIC", nullable = false)
	private String wert;

	public static Validation<Meldungen, BIC> aus(String wert)
	{
		return validiereString(wert, Meldung.BIC_LEER)
			.map(String::strip)
			.filter(BIC::pruefeLaenge)
			.getOrElse(Validation.invalid(Meldungen.aus(Meldung.BIC_UNGUELTIG)))
			.map(com.prowidesoftware.swift.model.BIC::new)
			.map(com.prowidesoftware.swift.model.BIC::getBic11)
			.map(BIC::new);
	}

	private static boolean pruefeLaenge(String wert)
	{
		return wert.length() == 8 || wert.length() == 11;
	}
}
