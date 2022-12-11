package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BenutzerService;
import de.justinharder.soq.domain.services.dto.NeuePrivatperson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("BenutzerRessource sollte")
class PrivatpersonenRessourceSollte
{
	private BenutzerService benutzerService;
	private NeuePrivatperson neuePrivatperson;

	@BeforeEach
	void setup()
	{
		benutzerService = mock(BenutzerService.class);
		neuePrivatperson = mock(NeuePrivatperson.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new PrivatpersonenRessource(null, neuePrivatperson)),
			() -> assertThrows(NullPointerException.class, () -> new PrivatpersonenRessource(benutzerService, null)));
	}
}
