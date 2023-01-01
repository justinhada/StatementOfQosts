package de.justinharder;

import de.justinharder.soq.domain.model.attribute.Herausgeber;
import de.justinharder.soq.domain.services.imports.model.*;

public class ImportTestdaten extends Testdaten
{
	protected static final Import IMPORT_1 = Import.aus(Herausgeber.OLB, DATEI_1).get();
	protected static final Import IMPORT_2 = Import.aus(Herausgeber.VRB, DATEI_2).get();
	protected static final OLBUmsatzDatum OLB_UMSATZ_DATUM = new OLBUmsatzDatum(
		"DE87280200504008357800",
		"31.10.2022",
		"31.10.2022",
		"Laura Tiemerding",
		"DE28280651080012888000",
		"GENODEF1DIK",
		"Wohnungsmiete",
		"447,48",
		"EUR",
		"NONREF",
		"",
		"0004770",
		"152",
		"DA-GUTSCHR");
	protected static final UmsatzDatum UMSATZ_DATUM_AUS_OLB = new UmsatzDatum(
		"DE87280200504008357800",
		"31.10.2022",
		"Laura Tiemerding",
		"DE28280651080012888000",
		"GENODEF1DIK",
		"Wohnungsmiete",
		"447,48");
	protected static final VRBUmsatzDatum VRB_UMSATZ_DATUM = new VRBUmsatzDatum(
		"VR Start",
		"DE28280651080012888000",
		"GENODEF1DIK",
		"VR BANK Dinklage-Steinfeld eG",
		"31.10.2022",
		"31.10.2022",
		"Justin Harder",
		"DE87280200504008357800",
		"OLBODEH2XXX",
		"Dauerauftragsbelast",
		"Wohnungsmiete /*DA-3* IBAN: DE87280200504008357800 BIC: OLBODEH2XXX",
		"-447,48",
		"EUR",
		"10.000,00",
		"",
		"Sonstiges",
		"",
		"",
		"");
	protected static final UmsatzDatum UMSATZ_DATUM_AUS_VRB = new UmsatzDatum(
		"DE28280651080012888000",
		"31.10.2022",
		"Justin Harder",
		"DE87280200504008357800",
		"OLBODEH2XXX",
		"Wohnungsmiete /*DA-3* IBAN: DE87280200504008357800 BIC: OLBODEH2XXX",
		"-447,48");
	protected static final UmsatzDaten UMSATZ_DATEN_1 = UmsatzDaten.aus(IMPORT_1).get();
}
