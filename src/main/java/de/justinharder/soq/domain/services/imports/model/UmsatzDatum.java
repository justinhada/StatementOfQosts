package de.justinharder.soq.domain.services.imports.model;

import com.google.common.base.MoreObjects;

public record UmsatzDatum(
	String auftraggeberIBAN,
	String datum,
	String zahlungsbeteiligterName,
	String zahlungsbeteiligterIBAN,
	String zahlungsbeteiligterBIC,
	String verwendungszweck,
	String betrag)
{
	public static UmsatzDatum aus(OLBUmsatzDatum olbUmsatzDatum)
	{
		return new UmsatzDatum(
			olbUmsatzDatum.inhaberkonto(),
			olbUmsatzDatum.buchungsdatum(),
			olbUmsatzDatum.auftraggeber(),
			olbUmsatzDatum.iban(),
			olbUmsatzDatum.bic(),
			olbUmsatzDatum.verwendungszweck(),
			olbUmsatzDatum.betrag());
	}

	public static UmsatzDatum aus(VRBUmsatzDatum vrbUmsatzDatum)
	{
		return new UmsatzDatum(
			vrbUmsatzDatum.ibanAuftragskonto(),
			vrbUmsatzDatum.buchungstag(),
			vrbUmsatzDatum.nameZahlungsbeteiligter(),
			vrbUmsatzDatum.ibanZahlungsbeteiligter(),
			vrbUmsatzDatum.bicZahlungsbeteiligter(),
			vrbUmsatzDatum.verwendungszweck(),
			vrbUmsatzDatum.betrag());
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
