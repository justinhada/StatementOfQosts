package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.*;
import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.model.meldung.Schluessel;

import javax.inject.Inject;
import javax.persistence.EntityManager;

class JpaRepositorySollte
{
	@Inject
	EntityManager entityManager;

	private <T extends Entitaet> T get(String id, Schluessel schluessel, Class<T> clazz)
	{
		return ID.aus(id, schluessel)
			.map(id1 -> entityManager.find(clazz, id1))
			.get();
	}

	private Bank getBank(String id)
	{
		return get(id, Schluessel.BANK, Bank.class);
	}

	protected Bank getBank1()
	{
		return getBank("46c317ae-25dd-4805-98ca-273e45d32815");
	}

	protected Bank getBank2()
	{
		return getBank("aaa8a25c-7589-4434-b668-4a78ab000628");
	}

	private Benutzer getBenutzer(String id)
	{
		return get(id, Schluessel.BENUTZER, Benutzer.class);
	}

	protected Benutzer getBenutzer1()
	{
		return getBenutzer("1eaa1624-69f3-4634-a96f-a3a9fd9c7bb4");
	}

	protected Benutzer getBenutzer2()
	{
		return getBenutzer("cd4fb688-8854-426f-a3e9-d5c380b4e984");
	}

	private Bankverbindung getBankverbindung(String id)
	{
		return get(id, Schluessel.BANKVERBINDUNG, Bankverbindung.class);
	}

	protected Bankverbindung getBankverbindung1()
	{
		return getBankverbindung("37854473-fa07-4570-a094-3794cd555aa4");
	}

	protected Bankverbindung getBankverbindung2()
	{
		return getBankverbindung("87ada42c-a758-4595-a158-f69ba73fd77a");
	}

	private Kontoinhaber getKontoinhaber(String id)
	{
		return get(id, Schluessel.KONTOINHABER, Kontoinhaber.class);
	}

	protected Kontoinhaber getKontoinhaber1()
	{
		return getKontoinhaber("bc01e451-051b-4bfa-979f-2acff87ba5a2");
	}

	protected Kontoinhaber getKontoinhaber2()
	{
		return getKontoinhaber("7f94d5e5-3972-4f44-b81b-994a244ba3ad");
	}

	private Kategorie getKategorie(String id)
	{
		return get(id, Schluessel.KATEGORIE, Kategorie.class);
	}

	protected Kategorie getKategorie1()
	{
		return getKategorie("c69a09ed-e5ff-4ac6-90dd-d319ff43b9d6");
	}

	protected Kategorie getKategorie2()
	{
		return getKategorie("8a977e9d-68e5-415e-948d-09b63b1e2907");
	}

	private Umsatz getUmsatz(String id)
	{
		return get(id, Schluessel.UMSATZ, Umsatz.class);
	}

	protected Umsatz getUmsatz1()
	{
		return getUmsatz("188fae37-0294-4db9-b7e6-7f40c7f390f1");
	}

	protected Umsatz getUmsatz2()
	{
		return getUmsatz("1da15420-5b77-4d06-9fad-e62c0b62bb6f");
	}

	private Buchung getBuchung(String id)
	{
		return get(id, Schluessel.BUCHUNG, Buchung.class);
	}

	protected Buchung getBuchung1()
	{
		return getBuchung("bbc67b44-9f75-4a8c-9271-6533f3e062e3");
	}

	protected Buchung getBuchung2()
	{
		return getBuchung("f1b01d7c-8fc2-4367-ae1e-d4042aa4bd9e");
	}
}
