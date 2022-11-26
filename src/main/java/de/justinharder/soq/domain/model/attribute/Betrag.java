package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.math.BigDecimal;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Betrag extends WertObjekt<BigDecimal>
{
	@Serial
	private static final long serialVersionUID = -5107368572132678397L;

	@NonNull
	@Column(name = "Betrag", nullable = false)
	private BigDecimal wert;

	public static Validation<Meldung, Betrag> aus(BigDecimal wert)
	{
		return validiere(wert, Meldung.BETRAG)
			.map(Betrag::new);
	}

	public boolean istNegativ()
	{
		return wert.compareTo(BigDecimal.ZERO) < 0;
	}
}
