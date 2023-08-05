package de.justinharder.soq.view;

import de.justinharder.soq.TestSollte;
import de.justinharder.soq.domain.services.KategorieService;
import de.justinharder.soq.domain.services.dto.NeueKategorie;
import de.justinharder.soq.view.theme.ThemeRessource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

@DisplayName("KategorieRessource sollte")
class KategorieRessourceSollte extends TestSollte
{
	private ThemeRessource themeRessource;
	private KategorieService kategorieService;
	private NeueKategorie neueKategorie;

	@BeforeEach
	void setup()
	{
		themeRessource = mock(ThemeRessource.class);
		kategorieService = mock(KategorieService.class);
		neueKategorie = mock(NeueKategorie.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		validiereNull(
			() -> new KategorienRessource(null, kategorieService, neueKategorie),
			() -> new KategorienRessource(themeRessource, null, neueKategorie),
			() -> new KategorienRessource(themeRessource, kategorieService, null));
	}
}
