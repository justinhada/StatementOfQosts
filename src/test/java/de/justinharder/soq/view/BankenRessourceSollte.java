package de.justinharder.soq.view;

import de.justinharder.soq.TestSollte;
import de.justinharder.soq.domain.services.BankService;
import de.justinharder.soq.domain.services.dto.GeloeschteBank;
import de.justinharder.soq.domain.services.dto.GespeicherteBank;
import de.justinharder.soq.domain.services.dto.NeueBank;
import de.justinharder.soq.view.theme.ThemeRessource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

@DisplayName("BankenRessource sollte")
class BankenRessourceSollte extends TestSollte
{
	private ThemeRessource themeRessource;
	private BankService bankService;
	private NeueBank neueBank;
	private GespeicherteBank gespeicherteBank;
	private GeloeschteBank geloeschteBank;

	@BeforeEach
	void setup()
	{
		themeRessource = mock(ThemeRessource.class);
		bankService = mock(BankService.class);
		neueBank = mock(NeueBank.class);
		gespeicherteBank = mock(GespeicherteBank.class);
		geloeschteBank = mock(GeloeschteBank.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		validiereNull(
			() -> new BankenRessource(null, bankService, neueBank, gespeicherteBank, geloeschteBank),
			() -> new BankenRessource(themeRessource, null, neueBank, gespeicherteBank, geloeschteBank),
			() -> new BankenRessource(themeRessource, bankService, null, gespeicherteBank, geloeschteBank),
			() -> new BankenRessource(themeRessource, bankService, neueBank, null, geloeschteBank),
			() -> new BankenRessource(themeRessource, bankService, neueBank, gespeicherteBank, null));
	}
}
