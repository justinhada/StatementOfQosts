package de.justinharder.soq.domain.services.imports.model;

import com.google.common.base.MoreObjects;

public record OLBUmsatzDaten(
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
	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("Inhaberkonto", inhaberkonto)
			.add("Buchungsdatum", buchungsdatum)
			.add("Valuta", valuta)
			.add("Auftraggeber", auftraggeber)
			.add("IBAN", iban)
			.add("BIC", bic)
			.add("Verwendungszweck", verwendungszweck)
			.add("Betrag", betrag)
			.add("Waehrung", waehrung)
			.add("Kundenreferenzen", kundenreferenzen)
			.add("Bankreferenz", bankreferenz)
			.add("Primatnota", primatnota)
			.add("TransaktionsCode", transaktionsCode)
			.add("TransaktionsText", transaktionsText)
			.toString();
	}
}
