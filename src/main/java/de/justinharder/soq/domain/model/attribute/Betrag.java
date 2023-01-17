package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

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

	public static Validation<Meldungen, Betrag> aus(BigDecimal wert)
	{
		return validiere(wert, Meldung.BETRAG_LEER)
			.map(bigDecimal -> bigDecimal.setScale(2, RoundingMode.HALF_UP))
			.map(Betrag::new);
	}

	public static Validation<Meldungen, Betrag> aus(String wert)
	{
		return validiereString(wert, Meldung.BETRAG_LEER)
			.flatMap(betrag -> {
				try
				{
					return Validation.valid(new BigDecimal(betrag.replace(",", ".")));
				}
				catch (Exception e)
				{
					return Validation.invalid(Meldungen.aus(Meldung.BETRAG_UNGUELTIG));
				}
			})
			.flatMap(Betrag::aus);
	}

	@Override
	public String toString()
	{
		var symbols = new DecimalFormatSymbols(Locale.GERMAN);
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		var format = new DecimalFormat("###,###.00", symbols);
		return format.format(getWert().setScale(2, RoundingMode.HALF_UP));
	}
}
