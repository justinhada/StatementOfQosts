package de.justinharder;

import de.justinharder.soq.domain.model.Umsatz;
import de.justinharder.soq.domain.model.attribute.Betrag;
import de.justinharder.soq.domain.model.attribute.Datum;
import de.justinharder.soq.domain.model.attribute.Herausgeber;
import de.justinharder.soq.domain.model.attribute.Verwendungszweck;
import de.justinharder.soq.domain.services.imports.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

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
	protected static final Datum UMSATZ_DATUM_DATUM = Datum.aus(LocalDate.of(2022, 10, 31)).get();
	protected static final Betrag UMSATZ_DATUM_BETRAG = Betrag.aus(new BigDecimal("447.48")).get();
	protected static final Verwendungszweck UMSATZ_DATUM_VERWENDUNGSZWECK = Verwendungszweck.aus("Wohnungsmiete").get();
	protected static final Umsatz UMSATZ = Umsatz.aus(
		UMSATZ_DATUM_DATUM,
		UMSATZ_DATUM_BETRAG,
		UMSATZ_DATUM_VERWENDUNGSZWECK,
		BANKVERBINDUNG_1,
		BANKVERBINDUNG_2).get();
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
		"Wohnungsmiete",
		"-447,48",
		"EUR",
		"10000,00",
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
		"Wohnungsmiete",
		"-447,48");
	protected static final UmsatzDaten UMSATZ_DATEN_1 = UmsatzDaten.aus(IMPORT_1).get();
}
