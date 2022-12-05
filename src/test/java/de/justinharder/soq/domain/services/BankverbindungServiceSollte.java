package de.justinharder.soq.domain.services;

import de.justinharder.DtoTestdaten;
import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BankRepository;
import de.justinharder.soq.domain.repository.BankverbindungRepository;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.services.dto.NeueBankverbindung;
import de.justinharder.soq.domain.services.mapping.BankverbindungMapping;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("BankverbindungService sollte")
class BankverbindungServiceSollte extends DtoTestdaten
{
	private BankverbindungService sut;

	private BankverbindungRepository bankverbindungRepository;
	private BenutzerRepository benutzerRepository;
	private BankRepository bankRepository;
	private BankverbindungMapping bankverbindungMapping;

	@BeforeEach
	void setup()
	{
		bankverbindungRepository = mock(BankverbindungRepository.class);
		benutzerRepository = mock(BenutzerRepository.class);
		bankRepository = mock(BankRepository.class);
		bankverbindungMapping = mock(BankverbindungMapping.class);

		sut = new BankverbindungService(
			bankverbindungRepository,
			benutzerRepository,
			bankRepository,
			bankverbindungMapping);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungService(null, benutzerRepository, bankRepository, bankverbindungMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungService(bankverbindungRepository, null, bankRepository, bankverbindungMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungService(bankverbindungRepository, benutzerRepository, null,
					bankverbindungMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungService(bankverbindungRepository, benutzerRepository, bankRepository, null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		when(bankverbindungRepository.findeAlle()).thenReturn(List.of(BANKVERBINDUNG_1, BANKVERBINDUNG_2));
		when(bankverbindungMapping.mappe(BANKVERBINDUNG_1)).thenReturn(GESPEICHERTE_BANKVERBINDUNG_1);
		when(bankverbindungMapping.mappe(BANKVERBINDUNG_2)).thenReturn(GESPEICHERTE_BANKVERBINDUNG_2);

		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(GESPEICHERTE_BANKVERBINDUNG_1,
			GESPEICHERTE_BANKVERBINDUNG_2);
	}

	@Test
	@DisplayName("leere Eingabedaten melden")
	void test03()
	{
		var ergebnis = sut.erstelle(new NeueBankverbindung(LEER, LEER, LEER));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.IBAN)).containsExactlyInAnyOrder(Meldung.IBAN_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZER)).containsExactlyInAnyOrder(
				Meldung.ID_LEER(Schluessel.BENUTZER)),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BANK)).containsExactlyInAnyOrder(
				Meldung.ID_LEER(Schluessel.BANK)),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("ungültige Eingabedaten melden")
	void test04()
	{
		var ergebnis = sut.erstelle(new NeueBankverbindung(
			"DE87280200504008357801",
			"1eaa1624-69f3-4634-a96f-a3a9fd9c7bb4-123",
			"46c317ae-25dd-4805-98ca-273e45d32815-123"));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.IBAN)).containsExactlyInAnyOrder(Meldung.IBAN_UNGUELTIG),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZER)).containsExactlyInAnyOrder(
				Meldung.ID_UNGUELTIG(Schluessel.BENUTZER)),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BANK)).containsExactlyInAnyOrder(
				Meldung.ID_UNGUELTIG(Schluessel.BANK)),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("bereits existierende IBAN und nicht existierende IDs melden")
	void test05()
	{
		when(bankverbindungRepository.istVorhanden(IBAN_1)).thenReturn(true);
		when(benutzerRepository.finde(BENUTZER_1.getId())).thenReturn(Option.none());
		when(bankRepository.finde(BANK_1.getId())).thenReturn(Option.none());

		var ergebnis = sut.erstelle(new NeueBankverbindung(
			IBAN_1_WERT,
			BENUTZER_1.getId().getWert().toString(),
			BANK_1.getId().getWert().toString()));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.IBAN)).containsExactlyInAnyOrder(
				Meldung.IBAN_EXISTIERT_BEREITS),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZER)).containsExactlyInAnyOrder(
				Meldung.BENUTZER_EXISTIERT_NICHT),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BANK)).containsExactlyInAnyOrder(
				Meldung.BANK_EXISTIERT_NICHT),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
		verify(bankverbindungRepository).istVorhanden(IBAN_1);
		verify(benutzerRepository).finde(BENUTZER_1.getId());
		verify(bankRepository).finde(BANK_1.getId());
	}

	@Test
	@DisplayName("erstellen")
	void test06()
	{
		when(bankverbindungRepository.istVorhanden(IBAN_1)).thenReturn(false);
		when(benutzerRepository.finde(BENUTZER_1.getId())).thenReturn(Option.of(BENUTZER_1));
		when(bankRepository.finde(BANK_1.getId())).thenReturn(Option.of(BANK_1));

		var ergebnis = sut.erstelle(new NeueBankverbindung(
			IBAN_1_WERT,
			BENUTZER_1.getId().getWert().toString(),
			BANK_1.getId().getWert().toString()));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.IBAN)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BENUTZER)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BANK)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.BANKVERBINDUNG_ERSTELLT));
		verify(bankverbindungRepository).istVorhanden(IBAN_1);
		verify(benutzerRepository).finde(BENUTZER_1.getId());
		verify(bankRepository).finde(BANK_1.getId());
		verify(bankverbindungRepository).speichere(any(Bankverbindung.class));
	}
}
