package de.justinharder.soq.domain.services;

import de.justinharder.DTOTestdaten;
import de.justinharder.soq.domain.model.Kategorie;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.KategorieRepository;
import de.justinharder.soq.domain.services.dto.NeueKategorie;
import de.justinharder.soq.domain.services.mapping.KategorieMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("KategorieService sollte")
class KategorieServiceSollte extends DTOTestdaten
{
	private KategorieService sut;

	private KategorieRepository kategorieRepository;
	private KategorieMapping kategorieMapping;

	@BeforeEach
	void setup()
	{
		kategorieRepository = mock(KategorieRepository.class);
		kategorieMapping = mock(KategorieMapping.class);

		sut = new KategorieService(kategorieRepository, kategorieMapping);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new KategorieService(kategorieRepository, null)),
			() -> assertThrows(NullPointerException.class, () -> new KategorieService(null, kategorieMapping)),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelle(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		when(kategorieRepository.findeAlle()).thenReturn(List.of(KATEGORIE_1, KATEGORIE_2));
		when(kategorieMapping.mappe(KATEGORIE_1)).thenReturn(GESPEICHERTE_KATEGORIE_1);
		when(kategorieMapping.mappe(KATEGORIE_2)).thenReturn(GESPEICHERTE_KATEGORIE_2);

		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(GESPEICHERTE_KATEGORIE_1, GESPEICHERTE_KATEGORIE_2);
		verify(kategorieRepository).findeAlle();
		verify(kategorieMapping).mappe(KATEGORIE_1);
		verify(kategorieMapping).mappe(KATEGORIE_2);
	}

	@Test
	@DisplayName("leere Eingabedaten melden")
	void test03()
	{
		var ergebnis = sut.erstelle(new NeueKategorie(LEER));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BEZEICHNUNG)).containsExactlyInAnyOrder(
				Meldung.BEZEICHNUNG_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("bereits existierende Daten melden")
	void test04()
	{
		when(kategorieRepository.istVorhanden(BEZEICHNUNG_3)).thenReturn(true);

		var ergebnis = sut.erstelle(new NeueKategorie(BEZEICHNUNG_3_WERT));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BEZEICHNUNG)).containsExactlyInAnyOrder(
				Meldung.BEZEICHNUNG_EXISTIERT_BEREITS),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
		verify(kategorieRepository).istVorhanden(BEZEICHNUNG_3);
	}

	@Test
	@DisplayName("erstellen")
	void test05()
	{
		when(kategorieRepository.istVorhanden(BEZEICHNUNG_3)).thenReturn(false);

		var ergebnis = sut.erstelle(new NeueKategorie(BEZEICHNUNG_3_WERT));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BEZEICHNUNG)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.KATEGORIE_ERSTELLT));
		verify(kategorieRepository).istVorhanden(BEZEICHNUNG_3);
		verify(kategorieRepository).speichere(any(Kategorie.class));
	}
}
