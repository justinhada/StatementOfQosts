package de.justinharder.soq.view;

import de.justinharder.soq.TestSollte;
import de.justinharder.soq.domain.services.BankverbindungService;
import de.justinharder.soq.domain.services.KontoinhaberService;
import de.justinharder.soq.domain.services.PrivatpersonService;
import de.justinharder.soq.domain.services.dto.NeuerKontoinhaber;
import de.justinharder.soq.view.theme.ThemeRessource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

@DisplayName("KontoinhaberRessource sollte")
class KontoinhaberRessourceSollte extends TestSollte
{
	private ThemeRessource themeRessource;
	private KontoinhaberService kontoinhaberService;
	private PrivatpersonService privatpersonService;
	private BankverbindungService bankverbindungService;
	private NeuerKontoinhaber neuerKontoinhaber;

	@BeforeEach
	void setup()
	{
		themeRessource = mock(ThemeRessource.class);
		kontoinhaberService = mock(KontoinhaberService.class);
		privatpersonService = mock(PrivatpersonService.class);
		bankverbindungService = mock(BankverbindungService.class);
		neuerKontoinhaber = mock(NeuerKontoinhaber.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		validiereNull(
			() -> new KontoinhaberRessource(null, kontoinhaberService, privatpersonService, bankverbindungService,
				neuerKontoinhaber),
			() -> new KontoinhaberRessource(themeRessource, null, privatpersonService, bankverbindungService,
				neuerKontoinhaber),
			() -> new KontoinhaberRessource(themeRessource, kontoinhaberService, null, bankverbindungService,
				neuerKontoinhaber),
			() -> new KontoinhaberRessource(themeRessource, kontoinhaberService, privatpersonService, null,
				neuerKontoinhaber),
			() -> new KontoinhaberRessource(themeRessource, kontoinhaberService, privatpersonService,
				bankverbindungService, null));
	}
}
