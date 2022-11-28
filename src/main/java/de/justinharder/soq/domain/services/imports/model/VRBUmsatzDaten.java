package de.justinharder.soq.domain.services.imports.model;

import com.google.common.base.MoreObjects;

public record VRBUmsatzDaten(
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
	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("BezeichnungAuftragskonto", bezeichnungAuftragskonto)
			.add("IBANAuftragskonto", ibanAuftragskonto)
			.add("BICAuftragskonto", bicAuftragskonto)
			.add("BanknameAuftragskonto", banknameAuftragskonto)
			.add("Buchungstag", buchungstag)
			.add("Valutadatum", valutadatum)
			.add("NameZahlungsbeteiligter", nameZahlungsbeteiligter)
			.add("IBANZahlungsbeteiligter", ibanZahlungsbeteiligter)
			.add("BICZahlungsbeteiligter", bicZahlungsbeteiligter)
			.add("Buchungstext", buchungstext)
			.add("Verwendungszweck", verwendungszweck)
			.add("Betrag", betrag)
			.add("Waehrung", waehrung)
			.add("SaldoNachBuchung", saldoNachBuchung)
			.add("Bemerkung", bemerkung)
			.add("Kategorie", kategorie)
			.add("Steuerrelevant", steuerrelevant)
			.add("GlaeubigerId", glaeubigerId)
			.add("Mandatsreferenz", mandatsreferenz)
			.toString();
	}
}
