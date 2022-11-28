package de.justinharder.soq.domain.services.imports.model.vrb;

import de.justinharder.soq.domain.services.imports.model.UmsatzDaten;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VrbUmsatzDaten //extends UmsatzDaten
{
	public static UmsatzDaten aus(
		String bezeichnungAuftragskonto,
		String ibanAuftragskonto,
		String bicAuftragskonto,
		String banknameAuftragskonto,
		String buchungstag,
		String valutadatum,
		String nameZahlungsbeteiligter,
		String ibanZahlungsbeteiligter,
		String bicZahlungsbeteiligter,
		String buchungstext,
		String verwendungszweck,
		String betrag,
		String waehrung,
		String saldoNachBuchung,
		String bemerkung,
		String kategorie,
		String steuerrelevant,
		String glaeubigerId,
		String mandatsreferenz)
	{
		return null;
	}
}
