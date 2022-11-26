package de.justinharder;

import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.Login;
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

	protected static final Benutzer JUSTIN_HARDER = Benutzer.aus(HARDER, JUSTIN).get();
	protected static final Benutzer LAURA_TIEMERDING = Benutzer.aus(TIEMERDING, LAURA).get();

	protected static final Benutzername B_HARDER = Benutzername.aus(HARDER_WERT).get();
	protected static final Benutzername B_TIEMERDING = Benutzername.aus(TIEMERDING_WERT).get();

	protected static final String E_JUSTIN_WERT = "justinharder@t-online.de";
	protected static final EMailAdresse E_JUSTIN = EMailAdresse.aus(E_JUSTIN_WERT).get();

	protected static final String E_LAURA_WERT = "laura.tiemerding@icloud.com";
	protected static final EMailAdresse E_LAURA = EMailAdresse.aus(E_LAURA_WERT).get();

	protected static final LocalDate D_01012020_WERT = LocalDate.of(2020, 1, 1);
	protected static final Datum D_01012020 = Datum.aus(D_01012020_WERT).get();

	protected static final LocalDate D_01012021_WERT = LocalDate.of(2021, 1, 1);
	protected static final Datum D_01012021 = Datum.aus(D_01012021_WERT).get();

	protected static final BigDecimal B_1_WERT = BigDecimal.ONE;
	protected static final Betrag B_1 = Betrag.aus(B_1_WERT).get();

	protected static final BigDecimal B_10_WERT = BigDecimal.TEN;
	protected static final Betrag B_10 = Betrag.aus(B_10_WERT).get();

	protected static final String OHNE_ESSEN_WERT = "Variable Kosten (ohne Essen)";
	protected static final Bezeichnung OHNE_ESSEN = Bezeichnung.aus(OHNE_ESSEN_WERT).get();

	protected static final String NUR_ESSEN_WERT = "Variable Kosten (nur Essen)";
	protected static final Bezeichnung NUR_ESSEN = Bezeichnung.aus(NUR_ESSEN_WERT).get();

	protected static final Salt SALT = Salt.random();

	protected static final String P_JUSTIN_WERT = "JustinHarder#98";
	protected static final String P_LAURA_WERT = "LauraTiemerding#98";

	protected static final Passwort P_JUSTIN = Passwort.aus(SALT, P_JUSTIN_WERT).get();
	protected static final Passwort P_LAURA = Passwort.aus(SALT, P_LAURA_WERT).get();

	protected static final Login LOGIN = Login.aus(E_JUSTIN, B_HARDER, SALT, P_JUSTIN, JUSTIN_HARDER).get();
}
