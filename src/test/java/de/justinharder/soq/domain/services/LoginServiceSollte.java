package de.justinharder.soq.domain.services;

import de.justinharder.DtoTestdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.LoginRepository;
import de.justinharder.soq.domain.services.dto.AngemeldeterBenutzer;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("LoginService sollte")
class LoginServiceSollte extends DtoTestdaten
{
	private LoginService sut;

	private LoginRepository loginRepository;

	@BeforeEach
	void setup()
	{
		loginRepository = mock(LoginRepository.class);

		sut = new LoginService(loginRepository);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertThrows(NullPointerException.class, () -> new LoginService(null));
	}

	@Test
	@DisplayName("nicht existierenden Benutzernamen melden")
	void test02()
	{
		var angemeldeterBenutzer = new AngemeldeterBenutzer(BENUTZERNAME_1_WERT, PASSWORT_1_WERT);
		when(loginRepository.finde(BENUTZERNAME_1)).thenReturn(Option.none());

		var ergebnis = sut.login(angemeldeterBenutzer);

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZERNAME)).containsExactlyInAnyOrder(
				Meldung.BENUTZERNAME_EXISTIERT_NICHT),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.PASSWORT)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
		verify(loginRepository).finde(BENUTZERNAME_1);
	}

	@Test
	@DisplayName("Benutzer anmelden")
	void test03()
	{
		var angemeldeterBenutzer = new AngemeldeterBenutzer(BENUTZERNAME_1_WERT, PASSWORT_1_WERT);
		when(loginRepository.finde(BENUTZERNAME_1)).thenReturn(Option.of(LOGIN_1));

		var ergebnis = sut.login(angemeldeterBenutzer);

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZERNAME)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.PASSWORT)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.LOGIN_ERFOLGREICH));
		verify(loginRepository).finde(BENUTZERNAME_1);
	}
}
