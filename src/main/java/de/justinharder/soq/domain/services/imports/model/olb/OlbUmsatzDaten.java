package de.justinharder.soq.domain.services.imports.model.olb;

import de.justinharder.soq.domain.services.imports.model.UmsatzDaten;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OlbUmsatzDaten //extends UmsatzDaten
{
	public static UmsatzDaten aus(
		String inhaberkonto,
		String buchungsdatum,
		String valuta,
		String auftraggeber,
		String iban,
		String bic,
		String verwendungszweck,
		String betrag,
		String waehrung,
		String kundenreferenzen,
		String bankreferenz,
		String primatnota,
		String transaktionsCode,
		String transaktionsText)
	{
		return null;
	}
}
