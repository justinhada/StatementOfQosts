package de.justinharder.soq.view;

import de.justinharder.soq.TestSollte;
import de.justinharder.soq.domain.services.PrivatpersonService;
import de.justinharder.soq.domain.services.dto.NeuePrivatperson;
import de.justinharder.soq.view.theme.ThemeRessource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

@DisplayName("PrivatpersonenRessource sollte")
class PrivatpersonenRessourceSollte extends TestSollte
{
	private ThemeRessource themeRessource;
	private PrivatpersonService privatpersonService;
	private NeuePrivatperson neuePrivatperson;

	@BeforeEach
	void setup()
	{
		themeRessource = mock(ThemeRessource.class);
		privatpersonService = mock(PrivatpersonService.class);
		neuePrivatperson = mock(NeuePrivatperson.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		validiereNull(
			() -> new PrivatpersonenRessource(null, privatpersonService, neuePrivatperson),
			() -> new PrivatpersonenRessource(themeRessource, null, neuePrivatperson),
			() -> new PrivatpersonenRessource(themeRessource, privatpersonService, null));
	}
}
