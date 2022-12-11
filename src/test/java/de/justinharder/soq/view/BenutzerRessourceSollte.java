package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BenutzerService;
import de.justinharder.soq.domain.services.dto.NeuerBenutzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("BenutzerRessource sollte")
class BenutzerRessourceSollte
{
	private BenutzerService benutzerService;
	private NeuerBenutzer neuerBenutzer;

	@BeforeEach
	void setup()
	{
		benutzerService = mock(BenutzerService.class);
		neuerBenutzer = mock(NeuerBenutzer.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new BenutzerRessource(null, neuerBenutzer)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerRessource(benutzerService, null)));
	}
}
