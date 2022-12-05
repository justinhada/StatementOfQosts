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
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new LoginService(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.login(null)));
	}

	@Test
	@DisplayName("leere Eingabedaten melden")
	void test02()
	{
		var ergebnis = sut.login(new AngemeldeterBenutzer(LEER, LEER));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZERNAME)).containsExactlyInAnyOrder(
				Meldung.BENUTZERNAME_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.PASSWORT)).containsExactlyInAnyOrder(
				Meldung.PASSWORT_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("falsches Passwort melden")
	void test03()
	{
		when(loginRepository.finde(BENUTZERNAME_1)).thenReturn(Option.of(LOGIN_1));

		var ergebnis = sut.login(new AngemeldeterBenutzer(BENUTZERNAME_1_WERT, PASSWORT_2_WERT));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZERNAME)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.PASSWORT)).containsExactlyInAnyOrder(
				Meldung.PASSWORT_FALSCH),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
		verify(loginRepository).finde(BENUTZERNAME_1);
	}

	@Test
	@DisplayName("Benutzer anmelden")
	void test04()
	{
		when(loginRepository.finde(BENUTZERNAME_1)).thenReturn(Option.of(LOGIN_1));

		var ergebnis = sut.login(new AngemeldeterBenutzer(BENUTZERNAME_1_WERT, PASSWORT_1_WERT));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZERNAME)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.PASSWORT)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.LOGIN_ERFOLGREICH));
		verify(loginRepository).finde(BENUTZERNAME_1);
	}

	@Test
	@DisplayName("nicht existierenden Benutzernamen melden")
	void test05()
	{
		when(loginRepository.finde(BENUTZERNAME_1)).thenReturn(Option.none());

		var ergebnis = sut.login(new AngemeldeterBenutzer(BENUTZERNAME_1_WERT, PASSWORT_1_WERT));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZERNAME)).containsExactlyInAnyOrder(
				Meldung.BENUTZERNAME_EXISTIERT_NICHT),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.PASSWORT)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
		verify(loginRepository).finde(BENUTZERNAME_1);
	}
}
