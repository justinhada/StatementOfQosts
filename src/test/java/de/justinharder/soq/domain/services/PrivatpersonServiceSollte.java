package de.justinharder.soq.domain.services;

import de.justinharder.DTOTestdaten;
import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.services.dto.NeuePrivatperson;
import de.justinharder.soq.domain.services.mapping.PrivatpersonMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("PrivatpersonService sollte")
class PrivatpersonServiceSollte extends DTOTestdaten
{
	private PrivatpersonService sut;

	private BenutzerRepository benutzerRepository;
	private PrivatpersonMapping privatpersonMapping;

	@BeforeEach
	void setup()
	{
		benutzerRepository = mock(BenutzerRepository.class);
		privatpersonMapping = mock(PrivatpersonMapping.class);

		sut = new PrivatpersonService(benutzerRepository, privatpersonMapping);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new PrivatpersonService(benutzerRepository, null)),
			() -> assertThrows(NullPointerException.class, () -> new PrivatpersonService(null, privatpersonMapping)),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelle(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		when(benutzerRepository.findeAlle()).thenReturn(List.of(BENUTZER_1, BENUTZER_2, BENUTZER_3, BENUTZER_4));
		when(privatpersonMapping.mappe(BENUTZER_1)).thenReturn(GESPEICHERTE_PRIVATPERSON_1);
		when(privatpersonMapping.mappe(BENUTZER_2)).thenReturn(GESPEICHERTE_PRIVATPERSON_2);

		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(GESPEICHERTE_PRIVATPERSON_1, GESPEICHERTE_PRIVATPERSON_2);
		verify(benutzerRepository).findeAlle();
		verify(privatpersonMapping).mappe(BENUTZER_1);
		verify(privatpersonMapping).mappe(BENUTZER_2);
	}

	@Test
	@DisplayName("invalide Privatperson nicht erstellen")
	void test03()
	{
		var ergebnis = sut.erstelle(new NeuePrivatperson(LEER, LEER));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.NACHNAME)).containsExactlyInAnyOrder(
				Meldung.NACHNAME_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.VORNAME)).containsExactlyInAnyOrder(Meldung.VORNAME_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("Privatperson mit existierendem Nach- und Vornamen nicht erstellen")
	void test04()
	{
		when(benutzerRepository.istVorhanden(NACHNAME_1, VORNAME_1)).thenReturn(true);

		var ergebnis = sut.erstelle(new NeuePrivatperson(NACHNAME_1_WERT, VORNAME_1_WERT));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.NACHNAME)).containsExactlyInAnyOrder(
				Meldung.NACHNAME_EXISTIERT_BEREITS),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.VORNAME)).containsExactlyInAnyOrder(
				Meldung.VORNAME_EXISTIERT_BEREITS),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
		verify(benutzerRepository).istVorhanden(NACHNAME_1, VORNAME_1);
	}

	@Test
	@DisplayName("Privatperson erstellen")
	void test05()
	{
		when(benutzerRepository.istVorhanden(NACHNAME_1, VORNAME_1)).thenReturn(false);

		var ergebnis = sut.erstelle(new NeuePrivatperson(NACHNAME_1_WERT, VORNAME_1_WERT));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.NACHNAME)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.VORNAME)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.PRIVATPERSON_ERSTELLT));
		verify(benutzerRepository).istVorhanden(NACHNAME_1, VORNAME_1);
		verify(benutzerRepository).speichere(any(Benutzer.class));
	}
}
