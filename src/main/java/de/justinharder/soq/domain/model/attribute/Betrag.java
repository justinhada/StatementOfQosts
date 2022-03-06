package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.model.meldung.Ebene;
import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.math.BigDecimal;

@Getter
@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Betrag extends WertObjekt<BigDecimal>
{
	@Serial
	private static final long serialVersionUID = -5107368572132678397L;

	@NonNull
	@Column(name = "Betrag")
	private BigDecimal wert;

	public static Validation<Meldung, Betrag> aus(BigDecimal wert)
	{
		return wert.compareTo(BigDecimal.ZERO) > 0
			? Validation.valid(new Betrag(wert))
			: Validation.invalid(new Meldung("betrag", Ebene.FEHLER, "Der Betrag darf nicht negativ sein!"));
	}
}
