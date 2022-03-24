package de.justinharder;

import de.justinharder.soq.domain.model.Kostenpunkt;
import de.justinharder.soq.domain.model.Person;
import de.justinharder.soq.domain.model.attribute.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Testdaten
{
	protected static final String HARDER_WERT = "Harder";
	protected static final Nachname HARDER = Nachname.aus(HARDER_WERT).get();

	protected static final String TIEMERDING_WERT = "Tiemerding";
	protected static final Nachname TIEMERDING = Nachname.aus(TIEMERDING_WERT).get();

	protected static final String JUSTIN_WERT = "Justin";
	protected static final Vorname JUSTIN = Vorname.aus(JUSTIN_WERT).get();

	protected static final String LAURA_WERT = "Laura";
	protected static final Vorname LAURA = Vorname.aus(LAURA_WERT).get();

	protected static final Person JUSTIN_HARDER = Person.aus(HARDER, JUSTIN).get();
	protected static final Person LAURA_TIEMERDING = Person.aus(TIEMERDING, LAURA).get();

	protected static final Benutzername B_HARDER = Benutzername.aus(HARDER_WERT).get();
	protected static final Benutzername B_TIEMERDING = Benutzername.aus(TIEMERDING_WERT).get();

	protected static final String E_JUSTIN_WERT = "justinharder@t-online.de";
	protected static final EmailAdresse E_JUSTIN = EmailAdresse.aus(E_JUSTIN_WERT).get();

	protected static final String E_LAURA_WERT = "laura.tiemerding@icloud.com";
	protected static final EmailAdresse E_LAURA = EmailAdresse.aus(E_LAURA_WERT).get();

	protected static final String EDEKA_WERT = "Edeka";
	protected static final Bezeichnung EDEKA = Bezeichnung.aus(EDEKA_WERT).get();

	protected static final String LIDL_WERT = "Lidl";
	protected static final Bezeichnung LIDL = Bezeichnung.aus(LIDL_WERT).get();

	protected static final LocalDate D_01012020_WERT = LocalDate.of(2020, 1, 1);
	protected static final Datum D_01012020 = Datum.aus(D_01012020_WERT).get();

	protected static final LocalDate D_01012021_WERT = LocalDate.of(2021, 1, 1);
	protected static final Datum D_01012021 = Datum.aus(D_01012021_WERT).get();

	protected static final BigDecimal B_1_WERT = BigDecimal.ONE;
	protected static final Betrag B_1 = Betrag.aus(B_1_WERT).get();

	protected static final BigDecimal B_10_WERT = BigDecimal.TEN;
	protected static final Betrag B_10 = Betrag.aus(B_10_WERT).get();

	protected static final String GEHALT_WERT = "Gehalt";
	protected static final Bezeichnung GEHALT = Bezeichnung.aus(GEHALT_WERT).get();

	protected static final String WEIHNACHTSGELD_WERT = "Weihnachtsgeld";
	protected static final Bezeichnung WEIHNACHTSGELD = Bezeichnung.aus(WEIHNACHTSGELD_WERT).get();

	protected static final Turnus MONATLICH = Turnus.MONATLICH;
	protected static final Turnus JAEHRLICH = Turnus.JAEHRLICH;

	protected static final String OHNE_ESSEN_WERT = "Variable Kosten (ohne Essen)";
	protected static final Bezeichnung OHNE_ESSEN = Bezeichnung.aus(OHNE_ESSEN_WERT).get();

	protected static final String NUR_ESSEN_WERT = "Variable Kosten (nur Essen)";
	protected static final Bezeichnung NUR_ESSEN = Bezeichnung.aus(NUR_ESSEN_WERT).get();

	protected static final Kostenpunkt K_1 = Kostenpunkt.aus(EDEKA, D_01012020, B_1, JUSTIN_HARDER).get();
	protected static final Kostenpunkt K_2 = Kostenpunkt.aus(LIDL, D_01012021, B_10, LAURA_TIEMERDING).get();

	protected static final Salt SALT = Salt.random();
}
