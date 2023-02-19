package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BankService;
import de.justinharder.soq.domain.services.dto.GeloeschteBank;
import de.justinharder.soq.domain.services.dto.GespeicherteBank;
import de.justinharder.soq.domain.services.dto.NeueBank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("BankenRessource sollte")
class BankenRessourceSollte
{
	private BankService bankService;
	private NeueBank neueBank;
	private GespeicherteBank gespeicherteBank;
	private GeloeschteBank geloeschteBank;

	@BeforeEach
	void setup()
	{
		bankService = mock(BankService.class);
		neueBank = mock(NeueBank.class);
		gespeicherteBank = mock(GespeicherteBank.class);
		geloeschteBank = mock(GeloeschteBank.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new BankenRessource(null, neueBank, gespeicherteBank, geloeschteBank)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankenRessource(bankService, null, gespeicherteBank, geloeschteBank)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankenRessource(bankService, neueBank, null, geloeschteBank)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankenRessource(bankService, neueBank, gespeicherteBank, null)));
	}
}
