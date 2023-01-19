package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.*;
import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import org.junit.jupiter.api.BeforeEach;

import javax.inject.Inject;
import javax.persistence.EntityManager;

class JpaRepositorySollte
{
	@Inject
	EntityManager entityManager;

	protected Bank bank1;
	protected Bank bank2;
	protected Benutzer benutzer1;
	protected Benutzer benutzer2;
	protected Benutzer benutzer3;
	protected Benutzer benutzer4;
	protected Bankverbindung bankverbindung1;
	protected Bankverbindung bankverbindung2;
	protected Kontoinhaber kontoinhaber1;
	protected Kontoinhaber kontoinhaber2;
	protected Kategorie kategorie1;
	protected Kategorie kategorie2;
	protected Umsatz umsatz1;
	protected Umsatz umsatz2;
	protected Buchung buchung1;
	protected Buchung buchung2;

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

	private Benutzer getBenutzer(String id)
	{
		return get(id, Schluessel.BENUTZER, Benutzer.class);
	}

	private Bankverbindung getBankverbindung(String id)
	{
		return get(id, Schluessel.BANKVERBINDUNG, Bankverbindung.class);
	}

	private Kontoinhaber getKontoinhaber(String id)
	{
		return get(id, Schluessel.KONTOINHABER, Kontoinhaber.class);
	}

	private Kategorie getKategorie(String id)
	{
		return get(id, Schluessel.KATEGORIE, Kategorie.class);
	}

	private Umsatz getUmsatz(String id)
	{
		return get(id, Schluessel.UMSATZ, Umsatz.class);
	}

	private Buchung getBuchung(String id)
	{
		return get(id, Schluessel.BUCHUNG, Buchung.class);
	}

	@BeforeEach
	void setup()
	{
		bank1 = getBank("46c317ae-25dd-4805-98ca-273e45d32815");
		bank2 = getBank("aaa8a25c-7589-4434-b668-4a78ab000628");
		benutzer1 = getBenutzer("1eaa1624-69f3-4634-a96f-a3a9fd9c7bb4");
		benutzer2 = getBenutzer("c09e5a5d-96c7-4607-b053-e8091a9481a7");
		benutzer3 = getBenutzer("cd4fb688-8854-426f-a3e9-d5c380b4e984");
		benutzer4 = getBenutzer("bb1d3c64-468d-4109-971c-24dcf08993fc");
		bankverbindung1 = getBankverbindung("37854473-fa07-4570-a094-3794cd555aa4");
		bankverbindung2 = getBankverbindung("87ada42c-a758-4595-a158-f69ba73fd77a");
		kontoinhaber1 = getKontoinhaber("bc01e451-051b-4bfa-979f-2acff87ba5a2");
		kontoinhaber2 = getKontoinhaber("7f94d5e5-3972-4f44-b81b-994a244ba3ad");
		kategorie1 = getKategorie("c69a09ed-e5ff-4ac6-90dd-d319ff43b9d6");
		kategorie2 = getKategorie("8a977e9d-68e5-415e-948d-09b63b1e2907");
		umsatz1 = getUmsatz("188fae37-0294-4db9-b7e6-7f40c7f390f1");
		umsatz2 = getUmsatz("1da15420-5b77-4d06-9fad-e62c0b62bb6f");
		buchung1 = getBuchung("bbc67b44-9f75-4a8c-9271-6533f3e062e3");
		buchung2 = getBuchung("f1b01d7c-8fc2-4367-ae1e-d4042aa4bd9e");
	}
}
