package de.justinharder.soq.view;

import de.justinharder.soq.TestSollte;
import de.justinharder.soq.domain.services.BankverbindungService;
import de.justinharder.soq.domain.services.BuchungService;
import de.justinharder.soq.domain.services.KategorieService;
import de.justinharder.soq.domain.services.UmsatzService;
import de.justinharder.soq.domain.services.dto.NeueBuchung;
import de.justinharder.soq.view.theme.ThemeRessource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

@DisplayName("BuchungenRessource sollte")
class BuchungenRessourceSollte extends TestSollte
{
	private ThemeRessource themeRessource;
	private BuchungService buchungService;
	private KategorieService kategorieService;
	private UmsatzService umsatzService;
	private BankverbindungService bankverbindungService;
	private NeueBuchung neueBuchung;

	@BeforeEach
	void setup()
	{
		themeRessource = mock(ThemeRessource.class);
		buchungService = mock(BuchungService.class);
		kategorieService = mock(KategorieService.class);
		umsatzService = mock(UmsatzService.class);
		bankverbindungService = mock(BankverbindungService.class);
		neueBuchung = mock(NeueBuchung.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		validiereNull(
			() -> new BuchungenRessource(null, buchungService, kategorieService, umsatzService, bankverbindungService,
				neueBuchung),
			() -> new BuchungenRessource(themeRessource, null, kategorieService, umsatzService, bankverbindungService,
				neueBuchung),
			() -> new BuchungenRessource(themeRessource, buchungService, null, umsatzService, bankverbindungService,
				neueBuchung),
			() -> new BuchungenRessource(themeRessource, buchungService, kategorieService, null, bankverbindungService,
				neueBuchung),
			() -> new BuchungenRessource(themeRessource, buchungService, kategorieService, umsatzService,
				bankverbindungService, null)
		);
	}
}
