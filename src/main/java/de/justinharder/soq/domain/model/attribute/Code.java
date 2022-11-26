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
public class Code extends WertObjekt<String>
{
	@Serial
	private static final long serialVersionUID = 3815710373281268849L;

	@NonNull
	@Column(name = "Code", nullable = false)
	private String wert;

	public static Validation<Meldung, Code> aus(String wert)
	{
		// TODO: Code richtig validieren! z. B. nur 3-stellig
		return validiereString(wert, Meldung.Code)
			.map(Code::new);
	}
}
