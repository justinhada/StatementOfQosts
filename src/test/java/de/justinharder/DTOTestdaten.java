package de.justinharder;

import de.justinharder.soq.domain.model.attribute.Herausgeber;
import de.justinharder.soq.domain.services.dto.*;

import java.time.format.DateTimeFormatter;

public class DTOTestdaten extends ImportTestdaten
{
	protected static final GespeicherteBank GESPEICHERTE_BANK_1 = new GespeicherteBank(
		BANK_1.getId().getWert().toString(),
		BANK_1.getBezeichnung().getWert(),
		BANK_1.getBic().getWert());
	protected static final GespeicherteBank GESPEICHERTE_BANK_2 = new GespeicherteBank(
		BANK_2.getId().getWert().toString(),
		BANK_2.getBezeichnung().getWert(),
		BANK_2.getBic().getWert());
	protected static final GespeichertePrivatperson GESPEICHERTE_PRIVATPERSON_1 = new GespeichertePrivatperson(
		BENUTZER_1.getId().getWert().toString(),
		BENUTZER_1.getNachname().getWert(),
		BENUTZER_1.getVorname().getWert());
	protected static final GespeichertePrivatperson GESPEICHERTE_PRIVATPERSON_2 = new GespeichertePrivatperson(
		BENUTZER_2.getId().getWert().toString(),
		BENUTZER_2.getNachname().getWert(),
		BENUTZER_2.getVorname().getWert());
	protected static final GespeichertesUnternehmen GESPEICHERTES_UNTERNEHMEN_1 = new GespeichertesUnternehmen(
		BENUTZER_3.getId().getWert().toString(),
		BENUTZER_3.getFirma().getWert());
	protected static final GespeichertesUnternehmen GESPEICHERTES_UNTERNEHMEN_2 = new GespeichertesUnternehmen(
		BENUTZER_4.getId().getWert().toString(),
		BENUTZER_4.getFirma().getWert());
	protected static final GespeicherteBankverbindung GESPEICHERTE_BANKVERBINDUNG_1 = new GespeicherteBankverbindung(
		BANKVERBINDUNG_1.getId().getWert().toString(),
		BANKVERBINDUNG_1.getIban().getWert(),
		BANKVERBINDUNG_1.getBank().getBezeichnung().getWert());
	protected static final GespeicherteBankverbindung GESPEICHERTE_BANKVERBINDUNG_2 = new GespeicherteBankverbindung(
		BANKVERBINDUNG_2.getId().getWert().toString(),
		BANKVERBINDUNG_2.getIban().getWert(),
		BANKVERBINDUNG_2.getBank().getBezeichnung().getWert());
	protected static final GespeicherteKategorie GESPEICHERTE_KATEGORIE_1 = new GespeicherteKategorie(
		KATEGORIE_1.getId().getWert().toString(),
		KATEGORIE_1.getBezeichnung().getWert());
	protected static final GespeicherteKategorie GESPEICHERTE_KATEGORIE_2 = new GespeicherteKategorie(
		KATEGORIE_2.getId().getWert().toString(),
		KATEGORIE_2.getBezeichnung().getWert());
	protected static final GespeicherterKontoinhaber GESPEICHERTER_KONTOINHABER_1 = new GespeicherterKontoinhaber(
		KONTOINHABER_1.getId().getWert().toString(),
		KONTOINHABER_1.getBenutzer().getNachname().getWert(),
		KONTOINHABER_1.getBenutzer().getVorname().getWert(),
		KONTOINHABER_1.getBankverbindung().getIban().getWert(),
		KONTOINHABER_1.getBankverbindung().getBank().getBezeichnung().getWert());
	protected static final GespeicherterKontoinhaber GESPEICHERTER_KONTOINHABER_2 = new GespeicherterKontoinhaber(
		KONTOINHABER_2.getId().getWert().toString(),
		KONTOINHABER_2.getBenutzer().getNachname().getWert(),
		KONTOINHABER_2.getBenutzer().getVorname().getWert(),
		KONTOINHABER_2.getBankverbindung().getIban().getWert(),
		KONTOINHABER_2.getBankverbindung().getBank().getBezeichnung().getWert());
	protected static final GespeicherterUmsatz GESPEICHERTER_UMSATZ_1 = new GespeicherterUmsatz(
		UMSATZ_1.getId().getWert().toString(),
		UMSATZ_1.getDatum().getWert().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
		"1,00",
		UMSATZ_1.getVerwendungszweck().getWert(),
		UMSATZ_1.getBankverbindungAuftraggeber().getIban().getWert(),
		UMSATZ_1.getBankverbindungZahlungsbeteiligter().getIban().getWert());
	protected static final GespeicherterUmsatz GESPEICHERTER_UMSATZ_2 = new GespeicherterUmsatz(
		UMSATZ_2.getId().getWert().toString(),
		UMSATZ_2.getDatum().getWert().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
		"-1,00",
		UMSATZ_2.getVerwendungszweck().getWert(),
		UMSATZ_2.getBankverbindungAuftraggeber().getIban().getWert(),
		UMSATZ_2.getBankverbindungZahlungsbeteiligter().getIban().getWert());
	protected static final NeuerImport NEUER_IMPORT =
		new NeuerImport(String.valueOf(Herausgeber.OLB.getCode()), DATEI_1_WERT);
	protected static final GespeicherterAuftraggeber GESPEICHERTER_AUFTRAGGEBER_1 = new GespeicherterAuftraggeber(
		BANKVERBINDUNG_1.getId().getWert().toString(),
		BANKVERBINDUNG_1.getIban().getWert(),
		BANKVERBINDUNG_1.getBank().getBezeichnung().getWert(),
		KONTOINHABER_1.getBenutzer().getVorname().getWert() + " " + KONTOINHABER_1.getBenutzer().getNachname()
			.getWert());
	protected static final GespeicherterAuftraggeber GESPEICHERTER_AUFTRAGGEBER_2 = new GespeicherterAuftraggeber(
		BANKVERBINDUNG_2.getId().getWert().toString(),
		BANKVERBINDUNG_2.getIban().getWert(),
		BANKVERBINDUNG_2.getBank().getBezeichnung().getWert(),
		KONTOINHABER_2.getBenutzer().getVorname().getWert() + " " + KONTOINHABER_2.getBenutzer().getNachname()
			.getWert());
	protected static final GespeicherterAuftraggeber GESPEICHERTER_AUFTRAGGEBER_3 = new GespeicherterAuftraggeber(
		BANKVERBINDUNG_3.getId().getWert().toString(),
		BANKVERBINDUNG_3.getIban().getWert(),
		BANKVERBINDUNG_3.getBank().getBezeichnung().getWert(),
		KONTOINHABER_3.getBenutzer().getFirma().getWert());
	protected static final GespeicherterAuftraggeber GESPEICHERTER_AUFTRAGGEBER_4 = new GespeicherterAuftraggeber(
		BANKVERBINDUNG_4.getId().getWert().toString(),
		BANKVERBINDUNG_4.getIban().getWert(),
		BANKVERBINDUNG_4.getBank().getBezeichnung().getWert(),
		KONTOINHABER_4.getBenutzer().getFirma().getWert());
	protected static final GespeicherterZahlungsbeteiligter GESPEICHERTER_ZAHLUNGSBETEILIGTER_1 =
		new GespeicherterZahlungsbeteiligter(
			BANKVERBINDUNG_1.getId().getWert().toString(),
			BANKVERBINDUNG_1.getIban().getWert(),
			BANKVERBINDUNG_1.getBank().getBezeichnung().getWert(),
			KONTOINHABER_1.getBenutzer().getVorname().getWert() + " " + KONTOINHABER_1.getBenutzer().getNachname()
				.getWert());
	protected static final GespeicherterZahlungsbeteiligter GESPEICHERTER_ZAHLUNGSBETEILIGTER_2 =
		new GespeicherterZahlungsbeteiligter(
			BANKVERBINDUNG_2.getId().getWert().toString(),
			BANKVERBINDUNG_2.getIban().getWert(),
			BANKVERBINDUNG_2.getBank().getBezeichnung().getWert(),
			KONTOINHABER_2.getBenutzer().getVorname().getWert() + " " + KONTOINHABER_2.getBenutzer().getNachname()
				.getWert());
	protected static final GespeicherterZahlungsbeteiligter GESPEICHERTER_ZAHLUNGSBETEILIGTER_3 =
		new GespeicherterZahlungsbeteiligter(
			BANKVERBINDUNG_3.getId().getWert().toString(),
			BANKVERBINDUNG_3.getIban().getWert(),
			BANKVERBINDUNG_3.getBank().getBezeichnung().getWert(),
			KONTOINHABER_3.getBenutzer().getFirma().getWert());
	protected static final GespeicherterZahlungsbeteiligter GESPEICHERTER_ZAHLUNGSBETEILIGTER_4 =
		new GespeicherterZahlungsbeteiligter(
			BANKVERBINDUNG_4.getId().getWert().toString(),
			BANKVERBINDUNG_4.getIban().getWert(),
			BANKVERBINDUNG_4.getBank().getBezeichnung().getWert(),
			KONTOINHABER_4.getBenutzer().getFirma().getWert());
	protected static final GespeicherteBuchung GESPEICHERTE_BUCHUNG_1 = new GespeicherteBuchung(
		BUCHUNG_1.getId().getWert().toString(),
		BUCHUNG_1.getUmsatz().getDatum().getWert().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
		"1,00",
		BUCHUNG_1.getKategorie().getBezeichnung().getWert());
	protected static final GespeicherteBuchung GESPEICHERTE_BUCHUNG_2 = new GespeicherteBuchung(
		BUCHUNG_2.getId().getWert().toString(),
		BUCHUNG_2.getUmsatz().getDatum().getWert().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
		"-1,00",
		BUCHUNG_2.getKategorie().getBezeichnung().getWert());
}
