package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BankService;
import de.justinharder.soq.domain.services.BankverbindungService;
import de.justinharder.soq.domain.services.BenutzerService;
import de.justinharder.soq.domain.services.dto.NeueBankverbindung;
import io.quarkus.qute.Template;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("BankverbindungenRessource sollte")
class BankverbindungenRessourceSollte
{
	private BankverbindungService bankverbindungService;
	private BenutzerService benutzerService;
	private BankService bankService;
	private Template bankverbindungen;
	private NeueBankverbindung neueBankverbindung;

	@BeforeEach
	void setup()
	{
		bankverbindungService = mock(BankverbindungService.class);
		benutzerService = mock(BenutzerService.class);
		bankService = mock(BankService.class);
		bankverbindungen = mock(Template.class);
		neueBankverbindung = mock(NeueBankverbindung.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungenRessource(null, benutzerService, bankService, bankverbindungen,
					neueBankverbindung)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungenRessource(bankverbindungService, null, bankService, bankverbindungen,
					neueBankverbindung)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungenRessource(bankverbindungService, benutzerService, null, bankverbindungen,
					neueBankverbindung)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungenRessource(bankverbindungService, benutzerService, bankService, null,
					neueBankverbindung)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungenRessource(bankverbindungService, benutzerService, bankService,
					bankverbindungen, null)));
	}
}
