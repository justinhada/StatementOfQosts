package de.justinharder.soq.domain.services;

import de.justinharder.DTOTestdaten;
import de.justinharder.soq.domain.model.Umsatz;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BankverbindungRepository;
import de.justinharder.soq.domain.repository.UmsatzRepository;
import de.justinharder.soq.domain.services.dto.NeuerUmsatz;
import de.justinharder.soq.domain.services.mapping.UmsatzMapping;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@DisplayName("UmsatzService sollte")
class UmsatzServiceSollte extends DTOTestdaten
{
	private UmsatzService sut;

	private UmsatzRepository umsatzRepository;
	private BankverbindungRepository bankverbindungRepository;
	private UmsatzMapping umsatzMapping;

	@BeforeEach
	void setup()
	{
		umsatzRepository = mock(UmsatzRepository.class);
		bankverbindungRepository = mock(BankverbindungRepository.class);
		umsatzMapping = mock(UmsatzMapping.class);

		sut = new UmsatzService(umsatzRepository, bankverbindungRepository, umsatzMapping);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new UmsatzService(null, bankverbindungRepository, umsatzMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new UmsatzService(umsatzRepository, null, umsatzMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new UmsatzService(umsatzRepository, bankverbindungRepository, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelle(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		when(umsatzRepository.findeAlle()).thenReturn(List.of(UMSATZ_1, UMSATZ_2));
		when(umsatzMapping.mappe(UMSATZ_1)).thenReturn(GESPEICHERTER_UMSATZ_1);
		when(umsatzMapping.mappe(UMSATZ_2)).thenReturn(GESPEICHERTER_UMSATZ_2);

		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(GESPEICHERTER_UMSATZ_1, GESPEICHERTER_UMSATZ_2);
		verify(umsatzRepository).findeAlle();
		verify(umsatzMapping).mappe(UMSATZ_1);
		verify(umsatzMapping).mappe(UMSATZ_2);
	}

	@Test
	@DisplayName("invaliden Umsatz nicht erstellen")
	void test03()
	{
		var bankverbindungId = BANKVERBINDUNG_1.getId().getWert().toString();
		var neuerUmsatz = new NeuerUmsatz(LEER, LEER, LEER, bankverbindungId, bankverbindungId);
		when(bankverbindungRepository.finde(BANKVERBINDUNG_1.getId())).thenReturn(Option.of(BANKVERBINDUNG_1));

		var ergebnis = sut.erstelle(neuerUmsatz);
		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.DATUM)).containsExactlyInAnyOrder(Meldung.DATUM_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BETRAG)).containsExactlyInAnyOrder(Meldung.BETRAG_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.VERWENDUNGSZWECK)).containsExactlyInAnyOrder(
				Meldung.VERWENDUNGSZWECK),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.AUFTRAGGEBER)).containsExactlyInAnyOrder(
				Meldung.AUFTRAGGEBER_GLEICH),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ZAHLUNGSBETEILIGTER)).containsExactlyInAnyOrder(
				Meldung.ZAHLUNGSBETEILIGTER_GLEICH),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
		verify(bankverbindungRepository, times(2)).finde(BANKVERBINDUNG_1.getId());
	}

	@Test
	@DisplayName("bereits existierenden Umsatz nicht erstellen")
	void test04()
	{
		var neuerUmsatz =
			new NeuerUmsatz(DATUM_1_WERT.format(DateTimeFormatter.ISO_DATE), BETRAG_1_STRING, VERWENDUNGSZWECK_1_WERT,
				BANKVERBINDUNG_1.getId().getWert().toString(), BANKVERBINDUNG_2.getId().getWert().toString());
		when(bankverbindungRepository.finde(BANKVERBINDUNG_1.getId())).thenReturn(Option.of(BANKVERBINDUNG_1));
		when(bankverbindungRepository.finde(BANKVERBINDUNG_2.getId())).thenReturn(Option.of(BANKVERBINDUNG_2));
		when(umsatzRepository.istVorhanden(DATUM_1, BETRAG_1, VERWENDUNGSZWECK_1, BANKVERBINDUNG_1,
			BANKVERBINDUNG_2)).thenReturn(true);

		var ergebnis = sut.erstelle(neuerUmsatz);
		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.DATUM)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BETRAG)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.VERWENDUNGSZWECK)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.AUFTRAGGEBER)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ZAHLUNGSBETEILIGTER)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.UMSATZ_EXISTIERT_BEREITS));
		verify(bankverbindungRepository).finde(BANKVERBINDUNG_1.getId());
		verify(bankverbindungRepository).finde(BANKVERBINDUNG_2.getId());
		verify(umsatzRepository).istVorhanden(DATUM_1, BETRAG_1, VERWENDUNGSZWECK_1, BANKVERBINDUNG_1,
			BANKVERBINDUNG_2);
	}

	@Test
	@DisplayName("Umsatz erstellen")
	void test05()
	{
		var neuerUmsatz =
			new NeuerUmsatz(DATUM_1_WERT.format(DateTimeFormatter.ISO_DATE), BETRAG_1_STRING, VERWENDUNGSZWECK_1_WERT,
				BANKVERBINDUNG_1.getId().getWert().toString(), BANKVERBINDUNG_2.getId().getWert().toString());
		when(bankverbindungRepository.finde(BANKVERBINDUNG_1.getId())).thenReturn(Option.of(BANKVERBINDUNG_1));
		when(bankverbindungRepository.finde(BANKVERBINDUNG_2.getId())).thenReturn(Option.of(BANKVERBINDUNG_2));
		when(umsatzRepository.istVorhanden(DATUM_1, BETRAG_1, VERWENDUNGSZWECK_1, BANKVERBINDUNG_1,
			BANKVERBINDUNG_2)).thenReturn(false);

		var ergebnis = sut.erstelle(neuerUmsatz);
		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.DATUM)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BETRAG)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.VERWENDUNGSZWECK)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.AUFTRAGGEBER)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ZAHLUNGSBETEILIGTER)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.UMSATZ_ERSTELLT));
		verify(bankverbindungRepository).finde(BANKVERBINDUNG_1.getId());
		verify(bankverbindungRepository).finde(BANKVERBINDUNG_2.getId());
		verify(umsatzRepository).istVorhanden(DATUM_1, BETRAG_1, VERWENDUNGSZWECK_1, BANKVERBINDUNG_1,
			BANKVERBINDUNG_2);
		verify(umsatzRepository).speichere(any(Umsatz.class));
	}
}
