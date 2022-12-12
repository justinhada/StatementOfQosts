package de.justinharder;

import de.justinharder.soq.domain.services.dto.*;

public class DtoTestdaten extends Testdaten
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
}
