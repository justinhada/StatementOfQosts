package de.justinharder.soq.view;

import de.justinharder.soq.TestSollte;
import de.justinharder.soq.domain.services.BankService;
import de.justinharder.soq.domain.services.BankverbindungService;
import de.justinharder.soq.domain.services.PrivatpersonService;
import de.justinharder.soq.domain.services.dto.NeueBankverbindung;
import de.justinharder.soq.view.theme.ThemeRessource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

@DisplayName("BankverbindungenRessource sollte")
class BankverbindungenResourceSollte extends TestSollte
{
	private ThemeRessource themeRessource;
	private BankverbindungService bankverbindungService;
	private PrivatpersonService privatpersonService;
	private BankService bankService;
	private NeueBankverbindung neueBankverbindung;

	@BeforeEach
	void setup()
	{
		themeRessource = mock(ThemeRessource.class);
		bankverbindungService = mock(BankverbindungService.class);
		privatpersonService = mock(PrivatpersonService.class);
		bankService = mock(BankService.class);
		neueBankverbindung = mock(NeueBankverbindung.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		validiereNull(
			() -> new BankverbindungenRessource(null, bankverbindungService, privatpersonService, bankService,
				neueBankverbindung),
			() -> new BankverbindungenRessource(themeRessource, null, privatpersonService, bankService,
				neueBankverbindung),
			() -> new BankverbindungenRessource(themeRessource, bankverbindungService, null, bankService,
				neueBankverbindung),
			() -> new BankverbindungenRessource(themeRessource, bankverbindungService, privatpersonService, null,
				neueBankverbindung),
			() -> new BankverbindungenRessource(themeRessource, bankverbindungService, privatpersonService, bankService,
				null));
	}
}
