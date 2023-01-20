package de.justinharder;

import de.justinharder.soq.domain.model.*;
import de.justinharder.soq.domain.model.attribute.*;
import de.justinharder.soq.domain.model.meldung.Schluessel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class Testdaten
{
	protected static final String LEER = "";
	protected static final String LEER_KURZ = " ";
	protected static final String LEER_LANG = "             ";
	// -- Attribute ----------------------------------------------------------------------------------------------------
	protected static final BigDecimal BETRAG_1_WERT = BigDecimal.ONE.setScale(2, RoundingMode.HALF_UP);
	protected static final String BETRAG_1_STRING = BETRAG_1_WERT.toString().replace(".", ",");
	protected static final Betrag BETRAG_1 = Betrag.aus(BETRAG_1_WERT).get();
	protected static final BigDecimal BETRAG_2_WERT = new BigDecimal(-1).setScale(2, RoundingMode.HALF_UP);
	protected static final String BETRAG_2_STRING = BETRAG_2_WERT.toString().replace(".", ",");
	protected static final Betrag BETRAG_2 = Betrag.aus(BETRAG_2_WERT).get();
	protected static final String BEZEICHNUNG_1_WERT = "Oldenburgische Landesbank AG";
	protected static final Bezeichnung BEZEICHNUNG_1 = Bezeichnung.aus(BEZEICHNUNG_1_WERT).get();
	protected static final String BEZEICHNUNG_2_WERT = "Volksbank Vechta eG";
	protected static final Bezeichnung BEZEICHNUNG_2 = Bezeichnung.aus(BEZEICHNUNG_2_WERT).get();
	protected static final String BEZEICHNUNG_3_WERT = "Lebensmittel";
	protected static final Bezeichnung BEZEICHNUNG_3 = Bezeichnung.aus(BEZEICHNUNG_3_WERT).get();
	protected static final String BEZEICHNUNG_4_WERT = "Supplements";
	protected static final Bezeichnung BEZEICHNUNG_4 = Bezeichnung.aus(BEZEICHNUNG_4_WERT).get();
	protected static final String BIC_1_WERT = "OLBODEH2XXX";
	protected static final BIC BIC_1 = BIC.aus(BIC_1_WERT).get();
	protected static final String BIC_2_WERT = "GENODEF1DIK";
	protected static final BIC BIC_2 = BIC.aus(BIC_2_WERT).get();
	protected static final String BIC_3_WERT = "OLBODEH2";
	protected static final BIC BIC_3 = BIC.aus(BIC_3_WERT).get();
	protected static final byte[] DATEI_1_WERT = """
		Inhaberkonto;Buchungsdatum;Valuta;Empfaenger/Auftraggeber;IBAN;BIC;Verwendungszweck;Betrag;Waehrung;Kundenreferenz;Bankreferenz;Primatnota;Transaktions-Code;Transaktions-Text
		DE87280200504008357800;31.10.2022;31.10.2022;Laura Tiemerding;DE28280651080012888000;GENODEF1DIK;Wohnungsmiete;447,48;EUR;NONREF;;0004770;152;DA-GUTSCHR
		""".getBytes(StandardCharsets.UTF_8);
	protected static final Datei DATEI_1 = Datei.aus(DATEI_1_WERT).get();
	protected static final byte[] DATEI_2_WERT = """
		Bezeichnung Auftragskonto;IBAN Auftragskonto;BIC Auftragskonto;Bankname Auftragskonto;Buchungstag;Valutadatum;Name Zahlungsbeteiligter;IBAN Zahlungsbeteiligter;BIC (SWIFT-Code) Zahlungsbeteiligter;Buchungstext;Verwendungszweck;Betrag;Waehrung;Saldo nach Buchung;Bemerkung;Kategorie;Steuerrelevant;Glaeubiger ID;Mandatsreferenz
		VR Start;DE28280651080012888000;GENODEF1DIK;VR BANK Dinklage-Steinfeld eG;31.10.2022;31.10.2022;Justin Harder;DE87280200504008357800;OLBODEH2XXX;Dauerauftragsbelast;Wohnungsmiete;-447,48;EUR;10000,00;;Sonstiges;;;
		""".getBytes(StandardCharsets.UTF_8);
	protected static final Datei DATEI_2 = Datei.aus(DATEI_2_WERT).get();
	protected static final LocalDate DATUM_1_WERT = LocalDate.of(2020, 1, 1);
	protected static final Datum DATUM_1 = Datum.aus(DATUM_1_WERT).get();
	protected static final LocalDate DATUM_2_WERT = LocalDate.of(2021, 1, 1);
	protected static final Datum DATUM_2 = Datum.aus(DATUM_2_WERT).get();
	protected static final String FIRMA_1_WERT = "Rewe-Markt GmbH";
	protected static final Firma FIRMA_1 = Firma.aus(FIRMA_1_WERT).get();
	protected static final String FIRMA_2_WERT = "ALTE OLDENBURGER Krankenversicherung AG";
	protected static final Firma FIRMA_2 = Firma.aus(FIRMA_2_WERT).get();
	protected static final String IBAN_1_WERT = "DE87280200504008357800";
	protected static final IBAN IBAN_1 = IBAN.aus(IBAN_1_WERT).get();
	protected static final String IBAN_2_WERT = "DE28280651080012888000";
	protected static final IBAN IBAN_2 = IBAN.aus(IBAN_2_WERT).get();
	protected static final String IBAN_3_WERT = "DE54500105174969128221";
	protected static final IBAN IBAN_3 = IBAN.aus(IBAN_3_WERT).get();
	protected static final String IBAN_4_WERT = "DE40500105179746466452";
	protected static final IBAN IBAN_4 = IBAN.aus(IBAN_4_WERT).get();
	protected static final String ID_1_WERT = "46c317ae-25dd-4805-98ca-273e45d32815";
	protected static final ID ID_1 = ID.aus(ID_1_WERT, Schluessel.BENUTZER).get();
	protected static final String NACHNAME_1_WERT = "Harder";
	protected static final Nachname NACHNAME_1 = Nachname.aus(NACHNAME_1_WERT).get();
	protected static final String NACHNAME_2_WERT = "Tiemerding";
	protected static final Nachname NACHNAME_2 = Nachname.aus(NACHNAME_2_WERT).get();
	protected static final String VERWENDUNGSZWECK_1_WERT = "Wohnungsmiete";
	protected static final Verwendungszweck VERWENDUNGSZWECK_1 = Verwendungszweck.aus(VERWENDUNGSZWECK_1_WERT).get();
	protected static final String VERWENDUNGSZWECK_2_WERT = "Lohn/Gehalt";
	protected static final Verwendungszweck VERWENDUNGSZWECK_2 = Verwendungszweck.aus(VERWENDUNGSZWECK_2_WERT).get();
	protected static final String VORNAME_1_WERT = "Justin";
	protected static final Vorname VORNAME_1 = Vorname.aus(VORNAME_1_WERT).get();
	protected static final String VORNAME_2_WERT = "Laura";
	protected static final Vorname VORNAME_2 = Vorname.aus(VORNAME_2_WERT).get();
	// -- Entitaeten ---------------------------------------------------------------------------------------------------
	protected static final Bank BANK_1 = Bank.aus(BEZEICHNUNG_1, BIC_1).get();
	protected static final Bank BANK_2 = Bank.aus(BEZEICHNUNG_1, BIC_1).get();
	protected static final Benutzer BENUTZER_1 = Benutzer.aus(NACHNAME_1, VORNAME_1).get();
	protected static final Benutzer BENUTZER_2 = Benutzer.aus(NACHNAME_2, VORNAME_2).get();
	protected static final Benutzer BENUTZER_3 = Benutzer.aus(FIRMA_1).get();
	protected static final Benutzer BENUTZER_4 = Benutzer.aus(FIRMA_2).get();
	protected static final Bankverbindung BANKVERBINDUNG_1 = Bankverbindung.aus(IBAN_1, BANK_1).get();
	protected static final Bankverbindung BANKVERBINDUNG_2 = Bankverbindung.aus(IBAN_2, BANK_2).get();
	protected static final Bankverbindung BANKVERBINDUNG_3 = Bankverbindung.aus(IBAN_3, BANK_1).get();
	protected static final Bankverbindung BANKVERBINDUNG_4 = Bankverbindung.aus(IBAN_4, BANK_2).get();
	protected static final Kategorie KATEGORIE_1 = Kategorie.aus(BEZEICHNUNG_3).get();
	protected static final Kategorie KATEGORIE_2 = Kategorie.aus(BEZEICHNUNG_4).get();
	protected static final Kontoinhaber KONTOINHABER_1 = Kontoinhaber.aus(BENUTZER_1, BANKVERBINDUNG_1).get();
	protected static final Kontoinhaber KONTOINHABER_2 = Kontoinhaber.aus(BENUTZER_2, BANKVERBINDUNG_2).get();
	protected static final Kontoinhaber KONTOINHABER_3 = Kontoinhaber.aus(BENUTZER_3, BANKVERBINDUNG_3).get();
	protected static final Kontoinhaber KONTOINHABER_4 = Kontoinhaber.aus(BENUTZER_4, BANKVERBINDUNG_4).get();
	protected static final Umsatz UMSATZ_1 =
		Umsatz.aus(DATUM_1, BETRAG_1, VERWENDUNGSZWECK_1, BANKVERBINDUNG_1, BANKVERBINDUNG_2).get();
	protected static final Umsatz UMSATZ_2 =
		Umsatz.aus(DATUM_2, BETRAG_2, VERWENDUNGSZWECK_2, BANKVERBINDUNG_2, BANKVERBINDUNG_1).get();
	protected static final Buchung BUCHUNG_1 = Buchung.aus(UMSATZ_1, KATEGORIE_1).get();
	protected static final Buchung BUCHUNG_2 = Buchung.aus(UMSATZ_2, KATEGORIE_2).get();
	// -----------------------------------------------------------------------------------------------------------------
}
