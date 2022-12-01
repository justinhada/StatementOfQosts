package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.LoginService;
import de.justinharder.soq.domain.services.dto.AngemeldeterBenutzer;
import io.quarkus.qute.Template;
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
	private Template login;
	private Template start;
	private AngemeldeterBenutzer angemeldeterBenutzer;

	@BeforeEach
	void setup()
	{
		loginService = mock(LoginService.class);
		login = mock(Template.class);
		start = mock(Template.class);
		angemeldeterBenutzer = mock(AngemeldeterBenutzer.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new LoginRessource(null, login, start, angemeldeterBenutzer)),
			() -> assertThrows(NullPointerException.class,
				() -> new LoginRessource(loginService, null, start, angemeldeterBenutzer)),
			() -> assertThrows(NullPointerException.class,
				() -> new LoginRessource(loginService, login, null, angemeldeterBenutzer)),
			() -> assertThrows(NullPointerException.class,
				() -> new LoginRessource(loginService, login, start, null)));
	}
}
