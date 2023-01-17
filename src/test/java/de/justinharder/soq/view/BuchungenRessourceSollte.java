package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BuchungService;
import de.justinharder.soq.domain.services.KategorieService;
import de.justinharder.soq.domain.services.UmsatzService;
import de.justinharder.soq.domain.services.dto.NeueBuchung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("BuchungenRessource sollte")
class BuchungenRessourceSollte
{
	private BuchungService buchungService;
	private KategorieService kategorieService;
	private UmsatzService umsatzService;
	private NeueBuchung neueBuchung;

	@BeforeEach
	void setup()
	{
		buchungService = mock(BuchungService.class);
		kategorieService = mock(KategorieService.class);
		umsatzService = mock(UmsatzService.class);
		neueBuchung = mock(NeueBuchung.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new BuchungenRessource(null, kategorieService, umsatzService, neueBuchung)),
			() -> assertThrows(NullPointerException.class,
				() -> new BuchungenRessource(buchungService, null, umsatzService, neueBuchung)),
			() -> assertThrows(NullPointerException.class,
				() -> new BuchungenRessource(buchungService, kategorieService, null, neueBuchung)),
			() -> assertThrows(NullPointerException.class,
				() -> new BuchungenRessource(buchungService, kategorieService, umsatzService, null)));
	}
}
