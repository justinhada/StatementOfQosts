package de.justinharder.soq.domain.services;

import de.justinharder.DTOTestdaten;
import de.justinharder.soq.domain.model.Kontoinhaber;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BankverbindungRepository;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.repository.KontoinhaberRepository;
import de.justinharder.soq.domain.services.dto.NeuerKontoinhaber;
import de.justinharder.soq.domain.services.mapping.KontoinhaberMapping;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("KontoinhaberService sollte")
class KontoinhaberServiceSollte extends DTOTestdaten
{
	private KontoinhaberService sut;

	private KontoinhaberRepository kontoinhaberRepository;
	private BenutzerRepository benutzerRepository;
	private BankverbindungRepository bankverbindungRepository;
	private KontoinhaberMapping kontoinhaberMapping;

	@BeforeEach
	void setup()
	{
		kontoinhaberRepository = mock(KontoinhaberRepository.class);
		benutzerRepository = mock(BenutzerRepository.class);
		bankverbindungRepository = mock(BankverbindungRepository.class);
		kontoinhaberMapping = mock(KontoinhaberMapping.class);

		sut = new KontoinhaberService(
			kontoinhaberRepository,
			benutzerRepository,
			bankverbindungRepository,
			kontoinhaberMapping);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new KontoinhaberService(null, benutzerRepository, bankverbindungRepository, kontoinhaberMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new KontoinhaberService(kontoinhaberRepository, null, bankverbindungRepository,
					kontoinhaberMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new KontoinhaberService(kontoinhaberRepository, benutzerRepository, null, kontoinhaberMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new KontoinhaberService(kontoinhaberRepository, benutzerRepository, bankverbindungRepository,
					null)),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelle(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		when(kontoinhaberRepository.findeAlle()).thenReturn(List.of(KONTOINHABER_1, KONTOINHABER_2));
		when(kontoinhaberMapping.mappe(KONTOINHABER_1)).thenReturn(GESPEICHERTER_KONTOINHABER_1);
		when(kontoinhaberMapping.mappe(KONTOINHABER_2)).thenReturn(GESPEICHERTER_KONTOINHABER_2);

		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(GESPEICHERTER_KONTOINHABER_1,
			GESPEICHERTER_KONTOINHABER_2);
		verify(kontoinhaberRepository).findeAlle();
		verify(kontoinhaberMapping).mappe(KONTOINHABER_1);
		verify(kontoinhaberMapping).mappe(KONTOINHABER_2);
	}

	@Test
	@DisplayName("leere Eingabedaten melden")
	void test03()
	{
		var ergebnis = sut.erstelle(new NeuerKontoinhaber(List.of(), LEER));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZER)).containsExactlyInAnyOrder(
				Meldung.BENUTZER_MINDESTAUSWAHL),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BANKVERBINDUNG)).containsExactlyInAnyOrder(
				Meldung.idLeer(Schluessel.BANKVERBINDUNG)),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("ungültige IDs melden")
	void test04()
	{
		var ergebnis = sut.erstelle(new NeuerKontoinhaber(
			List.of("2e8f6e24-7bd1-4b2b-974d"),
			"1dc3bd88-ce4d-4cc4-9f47"));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZER)).containsExactlyInAnyOrder(
				Meldung.idUngueltig(Schluessel.BENUTZER)),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BANKVERBINDUNG)).containsExactlyInAnyOrder(
				Meldung.idUngueltig(Schluessel.BANKVERBINDUNG)),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("nicht existierende IDs melden")
	void test05()
	{
		when(bankverbindungRepository.finde(BANKVERBINDUNG_1.getId())).thenReturn(Option.none());
		when(benutzerRepository.finde(BENUTZER_1.getId())).thenReturn(Option.none());
		var ergebnis = sut.erstelle(new NeuerKontoinhaber(
			List.of(BENUTZER_1.getId().getWert().toString()),
			BANKVERBINDUNG_1.getId().getWert().toString()));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZER)).containsExactlyInAnyOrder(
				Meldung.BENUTZER_EXISTIERT_NICHT),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BANKVERBINDUNG)).containsExactlyInAnyOrder(
				Meldung.BANKVERBINDUNG_EXISTIERT_NICHT),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
		verify(bankverbindungRepository).finde(BANKVERBINDUNG_1.getId());
		verify(benutzerRepository).finde(BENUTZER_1.getId());
	}

	@Test
	@DisplayName("bereits existierenden Kontoinhaber melden")
	void test06()
	{
		when(bankverbindungRepository.finde(BANKVERBINDUNG_1.getId())).thenReturn(Option.of(BANKVERBINDUNG_1));
		when(benutzerRepository.finde(BENUTZER_1.getId())).thenReturn(Option.of(BENUTZER_1));
		when(kontoinhaberRepository.istVorhanden(BENUTZER_1, BANKVERBINDUNG_1)).thenReturn(true);
		var ergebnis = sut.erstelle(new NeuerKontoinhaber(
			List.of(BENUTZER_1.getId().getWert().toString()),
			BANKVERBINDUNG_1.getId().getWert().toString()));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZER)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BANKVERBINDUNG)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.KONTOINHABER_EXISTIERT_BEREITS));
		verify(bankverbindungRepository).finde(BANKVERBINDUNG_1.getId());
		verify(benutzerRepository).finde(BENUTZER_1.getId());
		verify(kontoinhaberRepository).istVorhanden(BENUTZER_1, BANKVERBINDUNG_1);
	}

	@Test
	@DisplayName("erstellen")
	void test07()
	{
		when(bankverbindungRepository.finde(BANKVERBINDUNG_1.getId())).thenReturn(Option.of(BANKVERBINDUNG_1));
		when(benutzerRepository.finde(BENUTZER_1.getId())).thenReturn(Option.of(BENUTZER_1));
		when(kontoinhaberRepository.istVorhanden(BENUTZER_1, BANKVERBINDUNG_1)).thenReturn(false);
		var ergebnis = sut.erstelle(new NeuerKontoinhaber(
			List.of(BENUTZER_1.getId().getWert().toString()),
			BANKVERBINDUNG_1.getId().getWert().toString()));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZER)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BANKVERBINDUNG)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.KONTOINHABER_ERSTELLT));
		verify(bankverbindungRepository).finde(BANKVERBINDUNG_1.getId());
		verify(benutzerRepository).finde(BENUTZER_1.getId());
		verify(kontoinhaberRepository).istVorhanden(BENUTZER_1, BANKVERBINDUNG_1);
		verify(kontoinhaberRepository).speichere(any(Kontoinhaber.class));
	}
}
