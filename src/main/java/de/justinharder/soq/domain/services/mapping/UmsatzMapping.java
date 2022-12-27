package de.justinharder.soq.domain.services.mapping;

import de.justinharder.soq.domain.model.Umsatz;
import de.justinharder.soq.domain.model.attribute.Betrag;
import de.justinharder.soq.domain.services.dto.GespeicherterUmsatz;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Dependent
public class UmsatzMapping implements Mapping<Umsatz, GespeicherterUmsatz>
{
	@Override
	public GespeicherterUmsatz mappe(@NonNull Umsatz umsatz)
	{
		return new GespeicherterUmsatz(
			umsatz.getId().getWert().toString(),
			umsatz.getDatum().getWert().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
			mappe(umsatz.getBetrag()),
			umsatz.getVerwendungszweck().getWert(),
			umsatz.getBankverbindungAuftraggeber().getIban().getWert(),
			umsatz.getBankverbindungZahlungsbeteiligter().getIban().getWert());
	}

	private static String mappe(Betrag betrag)
	{
		var symbols = new DecimalFormatSymbols(Locale.GERMAN);
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		var format = new DecimalFormat("###,###.00", symbols);
		return format.format(betrag.getWert().setScale(2, RoundingMode.HALF_UP));
	}
}
