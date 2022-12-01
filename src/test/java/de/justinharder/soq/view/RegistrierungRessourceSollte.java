package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.RegistrierungService;
import de.justinharder.soq.domain.services.dto.NeuerBenutzer;
import io.quarkus.qute.Template;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("RegistrierungRessource sollte")
class RegistrierungRessourceSollte
{
	private RegistrierungService registrierungService;
	private Template registrierung;
	private NeuerBenutzer neuerBenutzer;

	@BeforeEach
	void setup()
	{
		registrierungService = mock(RegistrierungService.class);
		registrierung = mock(Template.class);
		neuerBenutzer = mock(NeuerBenutzer.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new RegistrierungRessource(null, registrierung, neuerBenutzer)),
			() -> assertThrows(NullPointerException.class,
				() -> new RegistrierungRessource(registrierungService, null, neuerBenutzer)),
			() -> assertThrows(NullPointerException.class,
				() -> new RegistrierungRessource(registrierungService, registrierung, null)));
	}
}
