package de.justinharder.soq.domain.model.meldung;

public record Meldung(Schluessel schluessel, Ebene ebene, String text)
{
	public static final Meldung BETRAG_LEER = new Meldung(
		Schluessel.BETRAG,
		Ebene.FEHLER,
		"Der Betrag darf nicht leer sein!");

	public static final Meldung BETRAG_NEGATIV = new Meldung(
		Schluessel.BETRAG,
		Ebene.FEHLER,
		"Der Betrag darf nicht negativ sein!");

	public static final Meldung BEZEICHNUNG = new Meldung(
		Schluessel.BEZEICHNUNG,
		Ebene.FEHLER,
		"Die Bezeichnung darf nicht leer sein!");

	public static final Meldung DATUM = new Meldung(
		Schluessel.DATUM,
		Ebene.FEHLER,
		"Das Datum darf nicht leer sein!");

	public static final Meldung NACHNAME = new Meldung(
		Schluessel.NACHNAME,
		Ebene.FEHLER,
		"Der Nachname darf nicht leer sein!");

	public static final Meldung PERSON = new Meldung(
		Schluessel.PERSON,
		Ebene.FEHLER,
		"Die Person darf nicht leer sein!");

	public static final Meldung TURNUS = new Meldung(
		Schluessel.TURNUS,
		Ebene.FEHLER,
		"Der Turnus darf nicht leer sein!");

	public static final Meldung VORNAME = new Meldung(
		Schluessel.VORNAME,
		Ebene.FEHLER,
		"Der Vorname darf nicht leer sein!");
}
