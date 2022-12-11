package de.justinharder.soq.domain.services;

import de.justinharder.DtoTestdaten;
import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.services.dto.NeuesUnternehmen;
import de.justinharder.soq.domain.services.mapping.UnternehmenMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("UnternehmenService sollte")
class UnternehmenServiceSollte extends DtoTestdaten
{
	private UnternehmenService sut;

	private BenutzerRepository benutzerRepository;
	private UnternehmenMapping unternehmenMapping;

	@BeforeEach
	void setup()
	{
		benutzerRepository = mock(BenutzerRepository.class);
		unternehmenMapping = mock(UnternehmenMapping.class);

		sut = new UnternehmenService(benutzerRepository, unternehmenMapping);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new UnternehmenService(benutzerRepository, null)),
			() -> assertThrows(NullPointerException.class, () -> new UnternehmenService(null, unternehmenMapping)),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelle(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		when(benutzerRepository.findeAlle()).thenReturn(List.of(BENUTZER_1, BENUTZER_2, BENUTZER_3, BENUTZER_4));
		when(unternehmenMapping.mappe(BENUTZER_3)).thenReturn(GESPEICHERTES_UNTERNEHMEN_1);
		when(unternehmenMapping.mappe(BENUTZER_4)).thenReturn(GESPEICHERTES_UNTERNEHMEN_2);

		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(GESPEICHERTES_UNTERNEHMEN_1, GESPEICHERTES_UNTERNEHMEN_2);
	}

	@Test
	@DisplayName("invalides Unternehmen nicht erstellen")
	void test03()
	{
		var ergebnis = sut.erstelle(new NeuesUnternehmen(LEER));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.FIRMA)).containsExactlyInAnyOrder(Meldung.FIRMA_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("Unternehmen mit existierender Firma nicht erstellen")
	void test04()
	{
		when(benutzerRepository.istVorhanden(FIRMA_1)).thenReturn(true);

		var ergebnis = sut.erstelle(new NeuesUnternehmen(FIRMA_1_WERT));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.FIRMA)).containsExactlyInAnyOrder(
				Meldung.FIRMA_EXISTIERT_BEREITS),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
		verify(benutzerRepository).istVorhanden(FIRMA_1);
	}

	@Test
	@DisplayName("Unternehmen erstellen")
	void test05()
	{
		when(benutzerRepository.istVorhanden(FIRMA_1)).thenReturn(false);

		var ergebnis = sut.erstelle(new NeuesUnternehmen(FIRMA_1_WERT));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.FIRMA)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.UNTERNEHMEN_ERSTELLT));
		verify(benutzerRepository).istVorhanden(FIRMA_1);
		verify(benutzerRepository).speichere(any(Benutzer.class));
	}
}
