package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BankService;
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

	@BeforeEach
	void setup()
	{
		bankService = mock(BankService.class);
		neueBank = mock(NeueBank.class);
		gespeicherteBank = mock(GespeicherteBank.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new BankenRessource(null, neueBank, gespeicherteBank)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankenRessource(bankService, null, gespeicherteBank)),
			() -> assertThrows(NullPointerException.class, () -> new BankenRessource(bankService, neueBank, null)));
	}
}
