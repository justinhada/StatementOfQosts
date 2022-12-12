package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.KategorieService;
import de.justinharder.soq.domain.services.dto.NeueKategorie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("KategorieRessource sollte")
class KategorieRessourceSollte
{
	private KategorieService kategorieService;
	private NeueKategorie neueKategorie;

	@BeforeEach
	void setup()
	{
		kategorieService = mock(KategorieService.class);
		neueKategorie = mock(NeueKategorie.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new KategorienRessource(null, neueKategorie)),
			() -> assertThrows(NullPointerException.class, () -> new KategorienRessource(kategorieService, null)));
	}
}
