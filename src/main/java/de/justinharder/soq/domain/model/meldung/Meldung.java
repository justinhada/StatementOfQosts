package de.justinharder.soq.domain.model.meldung;

public record Meldung(Schluessel schluessel, Ebene ebene, String text)
{
	public static final Meldung AUFTRAGGEBER_EXISTIERT_NICHT = new Meldung(
		Schluessel.AUFTRAGGEBER,
		Ebene.FEHLER,
		"Der Auftraggeber existiert nicht!");

	public static final Meldung AUFTRAGGEBER_GLEICH = new Meldung(
		Schluessel.AUFTRAGGEBER,
		Ebene.FEHLER,
		"Der Auftraggeber und der Zahlungsbeteiligte dürfen nicht gleich sein!");

	public static final Meldung AUFTRAGGEBER_LEER = new Meldung(
		Schluessel.AUFTRAGGEBER,
		Ebene.FEHLER,
		"Der Auftraggeber darf nicht leer sein!");

	public static final Meldung BANK_ERSTELLT = new Meldung(
		Schluessel.ALLGEMEIN,
		Ebene.ERFOLG,
		"Die Bank wurde erfolgreich erstellt!");

	public static final Meldung BANK_EXISTIERT_NICHT = new Meldung(
		Schluessel.BANK,
		Ebene.FEHLER,
		"Die Bank existiert nicht!");

	public static final Meldung BANK_LEER = new Meldung(
		Schluessel.BANK,
		Ebene.FEHLER,
		"Die Bank darf nicht leer sein!");

	public static final Meldung BANKVERBINDUNG_ERSTELLT = new Meldung(
		Schluessel.ALLGEMEIN,
		Ebene.ERFOLG,
		"Die Bankverbindung wurde erfolgreich erstellt!");

	public static final Meldung BANKVERBINDUNG_EXISTIERT_NICHT = new Meldung(
		Schluessel.BANKVERBINDUNG,
		Ebene.FEHLER,
		"Die Bankverbindung existiert nicht!");

	public static final Meldung BANKVERBINDUNG_LEER = new Meldung(
		Schluessel.BANKVERBINDUNG,
		Ebene.FEHLER,
		"Die Bankverbindung darf nicht leer sein!");

	public static final Meldung BENUTZER_EXISTIERT_NICHT = new Meldung(
		Schluessel.BENUTZER,
		Ebene.FEHLER,
		"Der Benutzer existiert nicht!");

	public static final Meldung BENUTZER_LEER = new Meldung(
		Schluessel.BENUTZER,
		Ebene.FEHLER,
		"Der Benutzer darf nicht leer sein!");

	public static final Meldung BENUTZER_MINDESTAUSWAHL = new Meldung(
		Schluessel.BENUTZER,
		Ebene.FEHLER,
		"Es muss mindestens ein Benutzer ausgewählt werden!");

	public static final Meldung BETRAG_LEER = new Meldung(
		Schluessel.BETRAG,
		Ebene.FEHLER,
		"Der Betrag darf nicht leer sein!");

	public static final Meldung BETRAG_UNGUELTIG = new Meldung(
		Schluessel.BETRAG,
		Ebene.FEHLER,
		"Der Betrag ist ungültig!");

	public static final Meldung BEZEICHNUNG_EXISTIERT_BEREITS = new Meldung(
		Schluessel.BEZEICHNUNG,
		Ebene.FEHLER,
		"Die Bezeichnung existiert bereits!");

	public static final Meldung BEZEICHNUNG_LEER = new Meldung(
		Schluessel.BEZEICHNUNG,
		Ebene.FEHLER,
		"Die Bezeichnung darf nicht leer sein!");

	public static final Meldung BIC_EXISTIERT_BEREITS = new Meldung(
		Schluessel.BIC,
		Ebene.FEHLER,
		"Der BIC (Bank Identifier Code) existiert bereits!");

	public static final Meldung BIC_LEER = new Meldung(
		Schluessel.BIC,
		Ebene.FEHLER,
		"Der BIC (Bank Identifier Code) darf nicht leer sein!");

	public static final Meldung BIC_UNGUELTIG = new Meldung(
		Schluessel.BIC,
		Ebene.FEHLER,
		"Der BIC (Bank Identifier Code) ist ungültig!");

	public static final Meldung DATEI = new Meldung(
		Schluessel.DATEI,
		Ebene.FEHLER,
		"Die Datei ist ungültig!");

	public static final Meldung DATUM_LEER = new Meldung(
		Schluessel.DATUM,
		Ebene.FEHLER,
		"Das Datum darf nicht leer sein!");

	public static final Meldung DATUM_UNGUELTIG = new Meldung(
		Schluessel.DATUM,
		Ebene.FEHLER,
		"Das Datum ist ungültig!");

	public static final Meldung FIRMA_EXISTIERT_BEREITS = new Meldung(
		Schluessel.FIRMA,
		Ebene.FEHLER,
		"Die Firma existiert bereits!");

	public static final Meldung FIRMA_LEER = new Meldung(
		Schluessel.FIRMA,
		Ebene.FEHLER,
		"Die Firma darf nicht leer sein!");

	public static final Meldung HERAUSGEBER_LEER = new Meldung(
		Schluessel.HERAUSGEBER,
		Ebene.FEHLER,
		"Der Herausgeber darf nicht leer sein!");

	public static final Meldung HERAUSGEBER_UNGUELTIG = new Meldung(
		Schluessel.HERAUSGEBER,
		Ebene.FEHLER,
		"Der Herausgeber ist ungültig!");

	public static final Meldung IBAN_EXISTIERT_BEREITS = new Meldung(
		Schluessel.IBAN,
		Ebene.FEHLER,
		"Die IBAN (Internationale Bankkontonummer) existiert bereits!");

	public static final Meldung IBAN_LEER = new Meldung(
		Schluessel.IBAN,
		Ebene.FEHLER,
		"Die IBAN (Internationale Bankkontonummer) darf nicht leer sein!");

	public static final Meldung IBAN_UNGUELTIG = new Meldung(
		Schluessel.IBAN,
		Ebene.FEHLER,
		"Die IBAN (Internationale Bankkontonummer) ist ungültig!");

	public static Meldung idLeer(Schluessel schluessel)
	{
		return new Meldung(schluessel, Ebene.FEHLER, "Die ID darf nicht leer sein!");
	}

	public static Meldung idUngueltig(Schluessel schluessel)
	{
		return new Meldung(schluessel, Ebene.FEHLER, "Die ID ist ungültig!");
	}

	public static final Meldung IMPORT = new Meldung(
		Schluessel.IMPORT,
		Ebene.FEHLER,
		"Der Import darf nicht leer sein!");

	public static final Meldung IMPORT_UNGUELTIG = new Meldung(
		Schluessel.ALLGEMEIN,
		Ebene.FEHLER,
		"Der Herausgeber und die Datei passen nicht zusammen!");

	public static final Meldung KATEGORIE_ERSTELLT = new Meldung(
		Schluessel.ALLGEMEIN,
		Ebene.ERFOLG,
		"Die Kategorie wurde erfolgreich erstellt!");

	public static final Meldung KATEGORIE_LEER = new Meldung(
		Schluessel.KATEGORIE,
		Ebene.FEHLER,
		"Die Kategorie darf nicht leer sein!");

	public static final Meldung KONTOINHABER_ERSTELLT = new Meldung(
		Schluessel.ALLGEMEIN,
		Ebene.ERFOLG,
		"Der Kontoinhaber wurde erfolgreich erstellt!");

	public static final Meldung KONTOINHABER_EXISTIERT_BEREITS = new Meldung(
		Schluessel.ALLGEMEIN,
		Ebene.FEHLER,
		"Der Kontoinhaber existiert bereits!");

	public static final Meldung NACHNAME_EXISTIERT_BEREITS = new Meldung(
		Schluessel.NACHNAME,
		Ebene.FEHLER,
		"Der Nachname in Kombination mit dem Vornamen existiert bereits!");

	public static final Meldung NACHNAME_LEER = new Meldung(
		Schluessel.NACHNAME,
		Ebene.FEHLER,
		"Der Nachname darf nicht leer sein!");

	public static final Meldung PRIVATPERSON_ERSTELLT = new Meldung(
		Schluessel.ALLGEMEIN,
		Ebene.ERFOLG,
		"Die Privatperson wurde erfolgreich erstellt!");

	public static final Meldung UMSATZ_ERSTELLT = new Meldung(
		Schluessel.ALLGEMEIN,
		Ebene.ERFOLG,
		"Der Umsatz wurde erfolgreich erstellt!");

	public static final Meldung UMSATZ_EXISTIERT_BEREITS = new Meldung(
		Schluessel.ALLGEMEIN,
		Ebene.FEHLER,
		"Der Umsatz existiert bereits!");

	public static final Meldung UMSATZ_EXISTIERT_NICHT = new Meldung(
		Schluessel.UMSATZ,
		Ebene.FEHLER,
		"Der Umsatz existiert nicht!");

	public static final Meldung UMSATZ_LEER = new Meldung(
		Schluessel.UMSATZ,
		Ebene.FEHLER,
		"Der Umsatz darf nicht leer sein!");

	public static final Meldung UNTERNEHMEN_ERSTELLT = new Meldung(
		Schluessel.ALLGEMEIN,
		Ebene.ERFOLG,
		"Das Unternehmen wurde erfolgreich erstellt!");

	public static final Meldung VERWENDUNGSZWECK = new Meldung(
		Schluessel.VERWENDUNGSZWECK,
		Ebene.FEHLER,
		"Der Verwendungszweck darf nicht leer sein!");

	public static final Meldung VORNAME_EXISTIERT_BEREITS = new Meldung(
		Schluessel.VORNAME,
		Ebene.FEHLER,
		"Der Vorname in Kombination mit dem Nachnamen existiert bereits!");

	public static final Meldung VORNAME_LEER = new Meldung(
		Schluessel.VORNAME,
		Ebene.FEHLER,
		"Der Vorname darf nicht leer sein!");

	public static final Meldung ZAHLUNGSBETEILIGTER_EXISTIERT_NICHT = new Meldung(
		Schluessel.ZAHLUNGSBETEILIGTER,
		Ebene.FEHLER,
		"Der Zahlungsbeteiligte existiert nicht!");

	public static final Meldung ZAHLUNGSBETEILIGTER_GLEICH = new Meldung(
		Schluessel.ZAHLUNGSBETEILIGTER,
		Ebene.FEHLER,
		"Der Auftraggeber und der Zahlungsbeteiligte dürfen nicht gleich sein!");

	public static final Meldung ZAHLUNGSBETEILIGTER_LEER = new Meldung(
		Schluessel.ZAHLUNGSBETEILIGTER,
		Ebene.FEHLER,
		"Der Zahlungsbeteiligte darf nicht leer sein!");

	public boolean istErfolg()
	{
		return ebene.equals(Ebene.ERFOLG);
	}

	public boolean istWarnung()
	{
		return ebene.equals(Ebene.WARNUNG);
	}

	public boolean istFehler()
	{
		return ebene.equals(Ebene.FEHLER);
	}
}
