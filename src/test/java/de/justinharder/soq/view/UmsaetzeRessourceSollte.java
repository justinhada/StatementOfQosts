package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.UmsatzService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("UmsaetzeRessource sollte")
class UmsaetzeRessourceSollte
{
	private UmsatzService umsatzService;

	@BeforeEach
	void setup()
	{
		umsatzService = mock(UmsatzService.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertThrows(NullPointerException.class, () -> new UmsaetzeRessource(null));
	}
}
