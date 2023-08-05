package de.justinharder.soq.view;

import de.justinharder.soq.TestSollte;
import de.justinharder.soq.domain.services.UnternehmenService;
import de.justinharder.soq.domain.services.dto.NeuesUnternehmen;
import de.justinharder.soq.view.theme.ThemeRessource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

@DisplayName("UnternehmenRessource sollte")
class UnternehmenRessourceSollte extends TestSollte
{
	private ThemeRessource themeRessource;
	private UnternehmenService unternehmenService;
	private NeuesUnternehmen neuesUnternehmen;

	@BeforeEach
	void setup()
	{
		themeRessource = mock(ThemeRessource.class);
		unternehmenService = mock(UnternehmenService.class);
		neuesUnternehmen = mock(NeuesUnternehmen.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		validiereNull(
			() -> new UnternehmenRessource(null, unternehmenService, neuesUnternehmen),
			() -> new UnternehmenRessource(themeRessource, null, neuesUnternehmen),
			() -> new UnternehmenRessource(themeRessource, unternehmenService, null));
	}
}
