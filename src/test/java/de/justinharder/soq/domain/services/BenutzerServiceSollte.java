package de.justinharder.soq.domain.services;

import de.justinharder.DtoTestdaten;
import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.services.dto.NeuerBenutzer;
import de.justinharder.soq.domain.services.mapping.BenutzerMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("BenutzerService sollte")
class BenutzerServiceSollte extends DtoTestdaten
{
	private BenutzerService sut;

	private BenutzerRepository benutzerRepository;
	private BenutzerMapping benutzerMapping;

	@BeforeEach
	void setup()
	{
		benutzerRepository = mock(BenutzerRepository.class);
		benutzerMapping = mock(BenutzerMapping.class);

		sut = new BenutzerService(benutzerRepository, benutzerMapping);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new BenutzerService(benutzerRepository, null)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerService(null, benutzerMapping)),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelle(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		when(benutzerRepository.findeAlle()).thenReturn(List.of(BENUTZER_1, BENUTZER_2));
		when(benutzerMapping.mappe(BENUTZER_1)).thenReturn(GESPEICHERTER_BENUTZER_1);
		when(benutzerMapping.mappe(BENUTZER_2)).thenReturn(GESPEICHERTER_BENUTZER_2);

		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(GESPEICHERTER_BENUTZER_1, GESPEICHERTER_BENUTZER_2);
	}

	@Test
	@DisplayName("invaliden Benutzer nicht registrieren")
	void test03()
	{
		var neuerBenutzer = new NeuerBenutzer(LEER, LEER);

		var ergebnis = sut.erstelle(neuerBenutzer);

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.NACHNAME)).containsExactlyInAnyOrder(Meldung.NACHNAME),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.VORNAME)).containsExactlyInAnyOrder(Meldung.VORNAME),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("Benutzer erstellen")
	void test04()
	{
		var neuerBenutzer = new NeuerBenutzer(NACHNAME_1_WERT, VORNAME_1_WERT);

		var ergebnis = sut.erstelle(neuerBenutzer);

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.NACHNAME)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.VORNAME)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.BENUTZER_ERSTELLT));
		verify(benutzerRepository).speichere(any(Benutzer.class));
	}
}
