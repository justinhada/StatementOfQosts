package de.justinharder;

import de.justinharder.soq.domain.services.dto.GespeicherteBank;
import de.justinharder.soq.domain.services.dto.GespeicherteBankverbindung;
import de.justinharder.soq.domain.services.dto.GespeicherterBenutzer;

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
	protected static final GespeicherterBenutzer GESPEICHERTER_BENUTZER_1 = new GespeicherterBenutzer(
		BENUTZER_1.getId().getWert().toString(),
		BENUTZER_1.getNachname().getWert(),
		BENUTZER_1.getVorname().getWert());
	protected static final GespeicherterBenutzer GESPEICHERTER_BENUTZER_2 = new GespeicherterBenutzer(
		BENUTZER_2.getId().getWert().toString(),
		BENUTZER_2.getNachname().getWert(),
		BENUTZER_2.getVorname().getWert());
	protected static final GespeicherteBankverbindung GESPEICHERTE_BANKVERBINDUNG_1 = new GespeicherteBankverbindung(
		BANKVERBINDUNG_1.getId().getWert().toString(),
		BANKVERBINDUNG_1.getIban().getWert(),
		BANKVERBINDUNG_1.getBank().getBezeichnung().getWert());
	protected static final GespeicherteBankverbindung GESPEICHERTE_BANKVERBINDUNG_2 = new GespeicherteBankverbindung(
		BANKVERBINDUNG_2.getId().getWert().toString(),
		BANKVERBINDUNG_2.getIban().getWert(),
		BANKVERBINDUNG_2.getBank().getBezeichnung().getWert());
}
