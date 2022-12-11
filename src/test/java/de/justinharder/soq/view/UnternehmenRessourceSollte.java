package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.UnternehmenService;
import de.justinharder.soq.domain.services.dto.NeuesUnternehmen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("UnternehmenRessource sollte")
class UnternehmenRessourceSollte
{
	private UnternehmenService unternehmenService;
	private NeuesUnternehmen neuesUnternehmen;

	@BeforeEach
	void setup()
	{
		unternehmenService = mock(UnternehmenService.class);
		neuesUnternehmen = mock(NeuesUnternehmen.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new UnternehmenRessource(null, neuesUnternehmen)),
			() -> assertThrows(NullPointerException.class, () -> new UnternehmenRessource(unternehmenService, null)));
	}
}
