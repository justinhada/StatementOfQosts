package de.justinharder.soq.view;

import de.justinharder.soq.TestSollte;
import de.justinharder.soq.domain.services.BankverbindungService;
import de.justinharder.soq.domain.services.KategorieService;
import de.justinharder.soq.domain.services.UmsatzService;
import de.justinharder.soq.domain.services.dto.NeuerUmsatz;
import de.justinharder.soq.view.theme.ThemeRessource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

@DisplayName("UmsaetzeRessource sollte")
class UmsaetzeRessourceSollte extends TestSollte
{
	private ThemeRessource themeRessource;
	private UmsatzService umsatzService;
	private BankverbindungService bankverbindungService;
	private KategorieService kategorieService;
	private NeuerUmsatz neuerUmsatz;

	@BeforeEach
	void setup()
	{
		themeRessource = mock(ThemeRessource.class);
		umsatzService = mock(UmsatzService.class);
		bankverbindungService = mock(BankverbindungService.class);
		kategorieService = mock(KategorieService.class);
		neuerUmsatz = mock(NeuerUmsatz.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		validiereNull(
			() -> new UmsaetzeRessource(null, umsatzService, bankverbindungService, kategorieService, neuerUmsatz),
			() -> new UmsaetzeRessource(themeRessource, null, bankverbindungService, kategorieService, neuerUmsatz),
			() -> new UmsaetzeRessource(themeRessource, umsatzService, null, kategorieService, neuerUmsatz),
			() -> new UmsaetzeRessource(themeRessource, umsatzService, bankverbindungService, null, neuerUmsatz),
			() -> new UmsaetzeRessource(themeRessource, umsatzService, bankverbindungService, kategorieService, null));
	}
}
