package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BankverbindungService;
import de.justinharder.soq.domain.services.KategorieService;
import de.justinharder.soq.domain.services.UmsatzService;
import de.justinharder.soq.domain.services.dto.NeuerUmsatz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("UmsaetzeRessource sollte")
class UmsaetzeRessourceSollte
{
	private UmsatzService umsatzService;
	private BankverbindungService bankverbindungService;
	private KategorieService kategorieService;
	private NeuerUmsatz neuerUmsatz;

	@BeforeEach
	void setup()
	{
		umsatzService = mock(UmsatzService.class);
		bankverbindungService = mock(BankverbindungService.class);
		kategorieService = mock(KategorieService.class);
		neuerUmsatz = mock(NeuerUmsatz.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new UmsaetzeRessource(null, bankverbindungService, kategorieService, neuerUmsatz)),
			() -> assertThrows(NullPointerException.class,
				() -> new UmsaetzeRessource(umsatzService, null, kategorieService, neuerUmsatz)),
			() -> assertThrows(NullPointerException.class,
				() -> new UmsaetzeRessource(umsatzService, bankverbindungService, null, neuerUmsatz)),
			() -> assertThrows(NullPointerException.class,
				() -> new UmsaetzeRessource(umsatzService, bankverbindungService, kategorieService, null)));
	}
}
