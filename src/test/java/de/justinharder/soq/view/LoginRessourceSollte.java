package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.LoginService;
import de.justinharder.soq.domain.services.dto.AngemeldeterBenutzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("LoginRessource sollte")
class LoginRessourceSollte
{
	private LoginService loginService;
	private AngemeldeterBenutzer angemeldeterBenutzer;

	@BeforeEach
	void setup()
	{
		loginService = mock(LoginService.class);
		angemeldeterBenutzer = mock(AngemeldeterBenutzer.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new LoginRessource(null, angemeldeterBenutzer)),
			() -> assertThrows(NullPointerException.class, () -> new LoginRessource(loginService, null)));
	}
}
