package de.justinharder;

import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.Login;
import de.justinharder.soq.domain.model.attribute.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Testdaten
{
	protected static final String LEER = "";
	protected static final String LEER_KURZ = " ";
	protected static final String LEER_LANG = "             ";
	// -- Attribute ----------------------------------------------------------------------------------------------------
	protected static final String BENUTZERNAME_1_WERT = "hard3r";
	protected static final Benutzername BENUTZERNAME_1 = Benutzername.aus(BENUTZERNAME_1_WERT).get();
	protected static final String BENUTZERNAME_2_WERT = "tiey";
	protected static final Benutzername BENUTZERNAME_2 = Benutzername.aus(BENUTZERNAME_2_WERT).get();
	protected static final BigDecimal BETRAG_1_WERT = BigDecimal.ONE;
	protected static final Betrag BETRAG_1 = Betrag.aus(BETRAG_1_WERT).get();
	protected static final BigDecimal BETRAG_2_WERT = new BigDecimal(-1);
	protected static final Betrag BETRAG_2 = Betrag.aus(BETRAG_2_WERT).get();
	protected static final String BEZEICHNUNG_1_WERT = "Oldenburgische Landesbank AG";
	protected static final Bezeichnung BEZEICHNUNG_1 = Bezeichnung.aus(BEZEICHNUNG_1_WERT).get();
	protected static final String BEZEICHNUNG_2_WERT = "Volksbank Vechta eG";
	protected static final Bezeichnung BEZEICHNUNG_2 = Bezeichnung.aus(BEZEICHNUNG_2_WERT).get();
	protected static final String BIC_1_WERT = "GENODEF1DIK";
	protected static final BIC BIC_1 = BIC.aus(BIC_1_WERT).get();
	protected static final String BIC_2_WERT = "OLBODEH2XXX";
	protected static final BIC BIC_2 = BIC.aus(BIC_2_WERT).get();
	protected static final LocalDate DATUM_1_WERT = LocalDate.of(2020, 1, 1);
	protected static final Datum DATUM_1 = Datum.aus(DATUM_1_WERT).get();
	protected static final LocalDate DATUM_2_WERT = LocalDate.of(2021, 1, 1);
	protected static final Datum DATUM_2 = Datum.aus(DATUM_2_WERT).get();
	protected static final String E_MAIL_ADRESSE_1_WERT = "justinharder@t-online.de";
	protected static final EMailAdresse E_MAIL_ADRESSE_1 = EMailAdresse.aus(E_MAIL_ADRESSE_1_WERT).get();
	protected static final String E_MAIL_ADRESSE_2_WERT = "laura.tiemerding@icloud.com";
	protected static final EMailAdresse E_MAIL_ADRESSE_2 = EMailAdresse.aus(E_MAIL_ADRESSE_2_WERT).get();
	protected static final String IBAN_1_WERT = "DE87280200504008357800";
	protected static final IBAN IBAN_1 = IBAN.aus(IBAN_1_WERT).get();
	protected static final String IBAN_2_WERT = "DE28280651080012888000";
	protected static final IBAN IBAN_2 = IBAN.aus(IBAN_2_WERT).get();
	protected static final String NACHNAME_1_WERT = "Harder";
	protected static final Nachname NACHNAME_1 = Nachname.aus(NACHNAME_1_WERT).get();
	protected static final String NACHNAME_2_WERT = "Tiemerding";
	protected static final Nachname NACHNAME_2 = Nachname.aus(NACHNAME_2_WERT).get();
	protected static final Salt SALT = Salt.random();
	protected static final String PASSWORT_1_WERT = "JustinHarder#98";
	protected static final Passwort PASSWORT_1 = Passwort.aus(SALT, PASSWORT_1_WERT).get();
	protected static final String PASSWORT_2_WERT = "LauraTiemerding#98";
	protected static final Passwort PASSWORT_2 = Passwort.aus(SALT, PASSWORT_2_WERT).get();
	protected static final String VERWENDUNGSZWECK_1_WERT = "Wohnungsmiete";
	protected static final Verwendungszweck VERWENDUNGSZWECK_1 = Verwendungszweck.aus(VERWENDUNGSZWECK_1_WERT).get();
	protected static final String VERWENDUNGSZWECK_2_WERT = "Lohn/Gehalt";
	protected static final Verwendungszweck VERWENDUNGSZWECK_2 = Verwendungszweck.aus(VERWENDUNGSZWECK_2_WERT).get();
	protected static final String VORNAME_1_WERT = "Justin";
	protected static final Vorname VORNAME_1 = Vorname.aus(VORNAME_1_WERT).get();
	protected static final String VORNAME_2_WERT = "Laura";
	protected static final Vorname VORNAME_2 = Vorname.aus(VORNAME_2_WERT).get();
	// -- Entitaeten ---------------------------------------------------------------------------------------------------
	protected static final Benutzer BENUTZER_1 = Benutzer.aus(NACHNAME_1, VORNAME_1).get();
	protected static final Benutzer BENUTZER_2 = Benutzer.aus(NACHNAME_2, VORNAME_2).get();
	protected static final Login LOGIN_1 = Login.aus(E_MAIL_ADRESSE_1, BENUTZERNAME_1, SALT, PASSWORT_1, BENUTZER_1).get();
	protected static final Login LOGIN_2 = Login.aus(E_MAIL_ADRESSE_2, BENUTZERNAME_2, SALT, PASSWORT_2, BENUTZER_2).get();
}
