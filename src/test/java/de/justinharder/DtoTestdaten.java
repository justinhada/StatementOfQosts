package de.justinharder;

import de.justinharder.soq.domain.services.dto.GespeicherteBank;

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
}
