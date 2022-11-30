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
	public static UmsatzDaten aus(OLBUmsatzDaten olbUmsatzDaten)
	{
		return new UmsatzDaten(
			olbUmsatzDaten.inhaberkonto(),
			olbUmsatzDaten.buchungsdatum(),
			olbUmsatzDaten.auftraggeber(),
			olbUmsatzDaten.iban(),
			olbUmsatzDaten.bic(),
			olbUmsatzDaten.verwendungszweck(),
			olbUmsatzDaten.betrag());
	}

	public static UmsatzDaten aus(VRBUmsatzDaten vrbUmsatzDaten)
	{
		return new UmsatzDaten(
			vrbUmsatzDaten.ibanAuftragskonto(),
			vrbUmsatzDaten.buchungstag(),
			vrbUmsatzDaten.nameZahlungsbeteiligter(),
			vrbUmsatzDaten.ibanZahlungsbeteiligter(),
			vrbUmsatzDaten.bicZahlungsbeteiligter(),
			vrbUmsatzDaten.verwendungszweck(),
			vrbUmsatzDaten.betrag());
	}

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
