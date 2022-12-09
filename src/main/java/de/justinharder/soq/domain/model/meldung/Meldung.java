package de.justinharder.soq.domain.model.meldung;

public record Meldung(Schluessel schluessel, Ebene ebene, String text)
{
	public static final Meldung ART = new Meldung(
		Schluessel.ART,
		Ebene.FEHLER,
		"Die Art darf nicht leer sein!");

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

	public static final Meldung BANKVERBINDUNG_LEER = new Meldung(
		Schluessel.BANKVERBINDUNG,
		Ebene.FEHLER,
		"Die Bankverbindung darf nicht leer sein!");

	public static final Meldung BENUTZER_ERSTELLT = new Meldung(
		Schluessel.ALLGEMEIN,
		Ebene.ERFOLG,
		"Der Benutzer wurde erfolgreich erstellt!");

	public static final Meldung BENUTZER_EXISTIERT_NICHT = new Meldung(
		Schluessel.BENUTZER,
		Ebene.FEHLER,
		"Der Benutzer existiert nicht!");

	public static final Meldung BENUTZER_LEER = new Meldung(
		Schluessel.BENUTZER,
		Ebene.FEHLER,
		"Der Benutzer darf nicht leer sein!");

	public static final Meldung BENUTZERNAME_EXISTIERT_NICHT = new Meldung(
		Schluessel.BENUTZERNAME,
		Ebene.FEHLER,
		"Der Benutzername existiert nicht!");

	public static final Meldung BENUTZERNAME_LEER = new Meldung(
		Schluessel.BENUTZERNAME,
		Ebene.FEHLER,
		"Der Benutzername darf nicht leer sein!");

	public static final Meldung BENUTZERNAME_EXISTIERT_BEREITS = new Meldung(
		Schluessel.BENUTZERNAME,
		Ebene.FEHLER,
		"Der Benutzername existiert bereits!");

	public static final Meldung BETRAG = new Meldung(
		Schluessel.BETRAG,
		Ebene.FEHLER,
		"Der Betrag darf nicht leer sein!");

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

	public static final Meldung DATUM = new Meldung(
		Schluessel.DATUM,
		Ebene.FEHLER,
		"Das Datum darf nicht leer sein!");

	public static final Meldung E_MAIL_ADRESSE_LEER = new Meldung(
		Schluessel.E_MAIL_ADRESSE,
		Ebene.FEHLER,
		"Die E-Mail-Adresse darf nicht leer sein!");

	public static final Meldung E_MAIL_ADRESSE_UNGUELTIG = new Meldung(
		Schluessel.E_MAIL_ADRESSE,
		Ebene.FEHLER,
		"Die E-Mail-Adresse ist ungültig!");

	public static final Meldung FIRMA = new Meldung(
		Schluessel.FIRMA,
		Ebene.FEHLER,
		"Die Firma darf nicht leer sein!");

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

	public static Meldung ID_LEER(Schluessel schluessel)
	{
		return new Meldung(schluessel, Ebene.FEHLER, "Die ID darf nicht leer sein!");
	}

	public static Meldung ID_UNGUELTIG(Schluessel schluessel)
	{
		return new Meldung(schluessel, Ebene.FEHLER, "Die ID ist ungültig!");
	}

	public static final Meldung KATEGORIE_LEER = new Meldung(
		Schluessel.KATEGORIE,
		Ebene.FEHLER,
		"Die Kategorie darf nicht leer sein!");

	public static final Meldung LOGIN_ERFOLGREICH = new Meldung(
		Schluessel.ALLGEMEIN,
		Ebene.ERFOLG,
		"Der Login war erfolgreich!");

	public static final Meldung NACHNAME = new Meldung(
		Schluessel.NACHNAME,
		Ebene.FEHLER,
		"Der Nachname darf nicht leer sein!");

	public static final Meldung PASSWORT_FALSCH = new Meldung(
		Schluessel.PASSWORT,
		Ebene.FEHLER,
		"Der Benutzername oder das Passwort ist falsch!");

	public static final Meldung PASSWORT_HASH = new Meldung(
		Schluessel.PASSWORT,
		Ebene.FEHLER,
		"Das Passwort konnte nicht gehasht werden!");

	public static final Meldung PASSWORT_LEER = new Meldung(
		Schluessel.PASSWORT,
		Ebene.FEHLER,
		"Das Passwort darf nicht leer sein!");

	public static final Meldung PASSWORT_UNGUELTIG = new Meldung(
		Schluessel.PASSWORT,
		Ebene.FEHLER,
		"Das Passwort ist ungültig!");

	public static final Meldung SALT = new Meldung(
		Schluessel.SALT,
		Ebene.FEHLER,
		"Der Salt darf nicht leer sein!");

	public static final Meldung UMSATZ_LEER = new Meldung(
		Schluessel.UMSATZ,
		Ebene.FEHLER,
		"Der Umsatz darf nicht leer sein!");

	public static final Meldung VERWENDUNGSZWECK = new Meldung(
		Schluessel.VERWENDUNGSZWECK,
		Ebene.FEHLER,
		"Der Verwendungszweck darf nicht leer sein!");

	public static final Meldung VORNAME = new Meldung(
		Schluessel.VORNAME,
		Ebene.FEHLER,
		"Der Vorname darf nicht leer sein!");
}
