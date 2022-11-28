package de.justinharder.soq.domain.services.imports.model;

import com.google.common.base.MoreObjects;

public record UmsatzDaten(
	String auftraggeberIBAN,
	String datum,
	String zahlungsbeteiligterName,
	String zahlungsbeteiligterIBAN,
	String zahlungsbeteiligterBIC,
	String verwendungszweck,
	String betrag)
{
	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("AuftraggeberIBAN", auftraggeberIBAN)
			.add("Datum", datum)
			.add("ZahlungsbeteiligterName", zahlungsbeteiligterName)
			.add("ZahlungsbeteiligterIBAN", zahlungsbeteiligterIBAN)
			.add("ZahlungsbeteiligterBIC", zahlungsbeteiligterBIC)
			.add("Verwendungszweck", verwendungszweck)
			.add("Betrag", betrag)
			.toString();
	}
}
