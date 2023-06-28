package de.justinharder.soq.domain.services;

import de.justinharder.DTOTestdaten;
import de.justinharder.soq.domain.model.Buchung;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BuchungRepository;
import de.justinharder.soq.domain.repository.KategorieRepository;
import de.justinharder.soq.domain.repository.UmsatzRepository;
import de.justinharder.soq.domain.services.dto.NeueBuchung;
import de.justinharder.soq.domain.services.mapping.BuchungMapping;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("BuchungService sollte")
class BuchungServiceSollte extends DTOTestdaten
{
	private BuchungService sut;

	private BuchungRepository buchungRepository;
	private UmsatzRepository umsatzRepository;
	private KategorieRepository kategorieRepository;
	private BuchungMapping buchungMapping;

	@BeforeEach
	void setup()
	{
		buchungRepository = mock(BuchungRepository.class);
		umsatzRepository = mock(UmsatzRepository.class);
		kategorieRepository = mock(KategorieRepository.class);
		buchungMapping = mock(BuchungMapping.class);

		sut = new BuchungService(buchungRepository, umsatzRepository, kategorieRepository, buchungMapping);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new BuchungService(null, umsatzRepository, kategorieRepository, buchungMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new BuchungService(buchungRepository, null, kategorieRepository, buchungMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new BuchungService(buchungRepository, umsatzRepository, null, buchungMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new BuchungService(buchungRepository, umsatzRepository, kategorieRepository, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelle(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		when(buchungRepository.findeAlle()).thenReturn(List.of(BUCHUNG_1, BUCHUNG_2));
		when(buchungMapping.mappe(BUCHUNG_1)).thenReturn(GESPEICHERTE_BUCHUNG_1);
		when(buchungMapping.mappe(BUCHUNG_2)).thenReturn(GESPEICHERTE_BUCHUNG_2);

		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(GESPEICHERTE_BUCHUNG_1, GESPEICHERTE_BUCHUNG_2);
		verify(buchungRepository).findeAlle();
		verify(buchungMapping).mappe(BUCHUNG_1);
		verify(buchungMapping).mappe(BUCHUNG_2);
	}

	@Test
	@DisplayName("nicht finden, wenn ID leer oder ungültig ist")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.finde(LEER).getMeldungen(Schluessel.BUCHUNG))
				.containsExactlyInAnyOrder(Meldung.idLeer(Schluessel.BUCHUNG)),
			() -> assertThat(sut.finde("3ef97d60-4a4d-4ef0-b7a6-9c1c3c06a3f3-123").getMeldungen(Schluessel.BUCHUNG))
				.containsExactlyInAnyOrder(Meldung.idUngueltig(Schluessel.BUCHUNG)));
	}

	@Test
	@DisplayName("nicht finden, wenn ID nicht existiert")
	void test04()
	{
		when(buchungRepository.finde(BUCHUNG_1.getId())).thenReturn(Option.none());

		assertThat(sut.finde(BUCHUNG_1.getId().getWert().toString()).getMeldungen(Schluessel.BUCHUNG))
			.containsExactlyInAnyOrder(Meldung.BUCHUNG_EXISTIERT_NICHT);
		verify(buchungRepository).finde(BUCHUNG_1.getId());
	}

	@Test
	@DisplayName("finden, wenn ID existiert")
	void test05()
	{
		when(buchungRepository.finde(BUCHUNG_1.getId())).thenReturn(Option.of(BUCHUNG_1));
		when(buchungMapping.mappe(BUCHUNG_1)).thenReturn(GESPEICHERTE_BUCHUNG_1);

		var ergebnis = sut.finde(BUCHUNG_1.getId().getWert().toString());

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BUCHUNG)).isEmpty(),
			() -> assertThat(ergebnis.getId()).isEqualTo(GESPEICHERTE_BUCHUNG_1.getId()),
			() -> assertThat(ergebnis.getKategorie()).isEqualTo(GESPEICHERTE_BUCHUNG_1.getKategorie()),
			() -> assertThat(ergebnis.getDatum()).isEqualTo(GESPEICHERTE_BUCHUNG_1.getDatum()),
			() -> assertThat(ergebnis.getBetrag()).isEqualTo(GESPEICHERTE_BUCHUNG_1.getBetrag()),
			() -> assertThat(ergebnis.getVerwendungszweck()).isEqualTo(GESPEICHERTE_BUCHUNG_1.getVerwendungszweck()),
			() -> assertThat(ergebnis.getAuftraggeber()).isEqualTo(GESPEICHERTE_BUCHUNG_1.getAuftraggeber()),
			() -> assertThat(ergebnis.getZahlungsbeteiligter()).isEqualTo(
				GESPEICHERTE_BUCHUNG_1.getZahlungsbeteiligter()));
		verify(buchungRepository).finde(BUCHUNG_1.getId());
		verify(buchungMapping).mappe(BUCHUNG_1);
	}

	@Test
	@DisplayName("nicht existierende IDs melden")
	void test06()
	{
		when(umsatzRepository.finde(UMSATZ_1.getId())).thenReturn(Option.none());
		when(kategorieRepository.finde(KATEGORIE_1.getId())).thenReturn(Option.none());

		var ergebnis = sut.erstelle(new NeueBuchung(
			UMSATZ_1.getId().getWert().toString(),
			KATEGORIE_1.getId().getWert().toString()));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.UMSATZ)).containsExactlyInAnyOrder(
				Meldung.UMSATZ_EXISTIERT_NICHT),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.KATEGORIE)).containsExactlyInAnyOrder(
				Meldung.KATEGORIE_EXISTIERT_NICHT),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
		verify(umsatzRepository).finde(UMSATZ_1.getId());
		verify(kategorieRepository).finde(KATEGORIE_1.getId());
	}

	@Test
	@DisplayName("bereits existierende Buchung nicht erstellen")
	void test07()
	{
		when(umsatzRepository.finde(UMSATZ_1.getId())).thenReturn(Option.of(UMSATZ_1));
		when(kategorieRepository.finde(KATEGORIE_1.getId())).thenReturn(Option.of(KATEGORIE_1));
		when(buchungRepository.istVorhanden(UMSATZ_1, KATEGORIE_1)).thenReturn(true);

		var ergebnis = sut.erstelle(new NeueBuchung(
			UMSATZ_1.getId().getWert().toString(),
			KATEGORIE_1.getId().getWert().toString()));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.UMSATZ)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.KATEGORIE)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.BUCHUNG_EXISTIERT_BEREITS));
		verify(umsatzRepository).finde(UMSATZ_1.getId());
		verify(kategorieRepository).finde(KATEGORIE_1.getId());
		verify(buchungRepository).istVorhanden(UMSATZ_1, KATEGORIE_1);
	}

	@Test
	@DisplayName("Buchung erstellen")
	void test08()
	{
		when(umsatzRepository.finde(UMSATZ_1.getId())).thenReturn(Option.of(UMSATZ_1));
		when(kategorieRepository.finde(KATEGORIE_1.getId())).thenReturn(Option.of(KATEGORIE_1));
		when(buchungRepository.istVorhanden(UMSATZ_1, KATEGORIE_1)).thenReturn(false);

		var ergebnis = sut.erstelle(new NeueBuchung(
			UMSATZ_1.getId().getWert().toString(),
			KATEGORIE_1.getId().getWert().toString()));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.UMSATZ)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.KATEGORIE)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.BUCHUNG_ERSTELLT));
		verify(umsatzRepository).finde(UMSATZ_1.getId());
		verify(kategorieRepository).finde(KATEGORIE_1.getId());
		verify(buchungRepository).istVorhanden(UMSATZ_1, KATEGORIE_1);
		verify(buchungRepository).speichere(any(Buchung.class));
	}
}
