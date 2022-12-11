package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.PrivatpersonService;
import de.justinharder.soq.domain.services.dto.NeuePrivatperson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("PrivatpersonenRessource sollte")
class PrivatpersonenRessourceSollte
{
	private PrivatpersonService privatpersonService;
	private NeuePrivatperson neuePrivatperson;

	@BeforeEach
	void setup()
	{
		privatpersonService = mock(PrivatpersonService.class);
		neuePrivatperson = mock(NeuePrivatperson.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new PrivatpersonenRessource(null, neuePrivatperson)),
			() -> assertThrows(NullPointerException.class, () -> new PrivatpersonenRessource(privatpersonService, null)));
	}
}
