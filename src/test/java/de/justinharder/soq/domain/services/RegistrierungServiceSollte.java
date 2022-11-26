package de.justinharder.soq.domain.services;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.Login;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.repository.LoginRepository;
import de.justinharder.soq.domain.services.dto.NeuerBenutzer;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("RegistrierungService sollte")
class RegistrierungServiceSollte extends Testdaten
{
	private RegistrierungService sut;

	private BenutzerRepository benutzerRepository;
	private LoginRepository loginRepository;

	@BeforeEach
	void setup()
	{
		benutzerRepository = mock(BenutzerRepository.class);
		loginRepository = mock(LoginRepository.class);

		sut = new RegistrierungService(benutzerRepository, loginRepository);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new RegistrierungService(null, loginRepository)),
			() -> assertThrows(NullPointerException.class, () -> new RegistrierungService(benutzerRepository, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.registriere(null)));
	}

	@Test
	@DisplayName("invaliden Benutzer und Login nicht registrieren")
	void test02()
	{
		var neuerBenutzer = new NeuerBenutzer(LEER, LEER, LEER, LEER, LEER);

		var ergebnis = sut.registriere(neuerBenutzer);

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.E_MAIL_ADRESSE)).containsExactlyInAnyOrder(
				Meldung.E_MAIL_ADRESSE_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZERNAME)).containsExactlyInAnyOrder(
				Meldung.BENUTZERNAME_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.NACHNAME)).containsExactlyInAnyOrder(Meldung.NACHNAME),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.VORNAME)).containsExactlyInAnyOrder(Meldung.VORNAME),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.PASSWORT)).containsExactlyInAnyOrder(
				Meldung.PASSWORT_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("invaliden Benutzer und Login nicht registrieren")
	void test03()
	{
		var neuerBenutzer = new NeuerBenutzer("invalideEMailAdresse", LEER, LEER, LEER, LEER);

		var ergebnis = sut.registriere(neuerBenutzer);

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.E_MAIL_ADRESSE)).containsExactlyInAnyOrder(
				Meldung.E_MAIL_ADRESSE_UNGUELTIG),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZERNAME)).containsExactlyInAnyOrder(
				Meldung.BENUTZERNAME_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.NACHNAME)).containsExactlyInAnyOrder(Meldung.NACHNAME),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.VORNAME)).containsExactlyInAnyOrder(Meldung.VORNAME),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.PASSWORT)).containsExactlyInAnyOrder(
				Meldung.PASSWORT_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("Benutzer und Login mit vergebenem Benutzernamen nicht registrieren")
	void test04()
	{
		var neuerBenutzer = new NeuerBenutzer(E_MAIL_ADRESSE_1_WERT, BENUTZERNAME_1_WERT, NACHNAME_1_WERT,
			VORNAME_1_WERT, PASSWORT_1_WERT);
		when(loginRepository.finde(BENUTZERNAME_1)).thenReturn(Option.of(LOGIN_1));

		var ergebnis = sut.registriere(neuerBenutzer);

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.E_MAIL_ADRESSE)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZERNAME)).containsExactlyInAnyOrder(
				Meldung.BENUTZERNAME_VERGEBEN),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.NACHNAME)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.VORNAME)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.PASSWORT)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
		verify(loginRepository).finde(BENUTZERNAME_1);
	}

	@Test
	@DisplayName("Benutzer und Login registrieren")
	void test05()
	{
		var neuerBenutzer = new NeuerBenutzer(E_MAIL_ADRESSE_1_WERT, BENUTZERNAME_1_WERT, NACHNAME_1_WERT,
			VORNAME_1_WERT, PASSWORT_1_WERT);
		when(loginRepository.finde(BENUTZERNAME_1)).thenReturn(Option.none());

		var ergebnis = sut.registriere(neuerBenutzer);

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.E_MAIL_ADRESSE)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZERNAME)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.NACHNAME)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.VORNAME)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.PASSWORT)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.BENUTZER_ERSTELLT));
		verify(loginRepository).finde(BENUTZERNAME_1);
		verify(benutzerRepository).speichere(any(Benutzer.class));
		verify(loginRepository).speichere(any(Login.class));
	}
}
