package de.justinharder.soq.domain.services.imports;

import de.justinharder.soq.domain.services.imports.model.OLBUmsatzDaten;
import de.justinharder.soq.domain.services.imports.model.UmsatzDaten;
import de.justinharder.soq.domain.services.imports.model.VRBUmsatzDaten;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Import
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
}
