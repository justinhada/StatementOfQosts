package de.justinharder.soq.domain.services;

import de.justinharder.DTOTestdaten;
import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BankRepository;
import de.justinharder.soq.domain.repository.BankverbindungRepository;
import de.justinharder.soq.domain.repository.KontoinhaberRepository;
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
class BankverbindungServiceSollte extends DTOTestdaten
{
	private BankverbindungService sut;

	private BankverbindungRepository bankverbindungRepository;
	private BankRepository bankRepository;
	private KontoinhaberRepository kontoinhaberRepository;
	private BankverbindungMapping bankverbindungMapping;

	@BeforeEach
	void setup()
	{
		bankverbindungRepository = mock(BankverbindungRepository.class);
		bankRepository = mock(BankRepository.class);
		kontoinhaberRepository = mock(KontoinhaberRepository.class);
		bankverbindungMapping = mock(BankverbindungMapping.class);

		sut = new BankverbindungService(
			bankverbindungRepository,
			bankRepository,
			kontoinhaberRepository,
			bankverbindungMapping);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungService(null, bankRepository, kontoinhaberRepository, bankverbindungMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungService(bankverbindungRepository, null, kontoinhaberRepository,
					bankverbindungMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungService(bankverbindungRepository, bankRepository, null, bankverbindungMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungService(bankverbindungRepository, bankRepository, kontoinhaberRepository,
					null)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelle(null)));
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
		verify(bankverbindungRepository).findeAlle();
		verify(bankverbindungMapping).mappe(BANKVERBINDUNG_1);
		verify(bankverbindungMapping).mappe(BANKVERBINDUNG_2);
	}

	@Test
	@DisplayName("nicht finden, wenn ID nicht existiert")
	void test03()
	{
		when(bankverbindungRepository.finde(BANKVERBINDUNG_1.getId())).thenReturn(Option.none());

		assertThat(sut.finde(BANKVERBINDUNG_1.getId().getWert().toString()).getMeldungen(Schluessel.BANKVERBINDUNG))
			.containsExactlyInAnyOrder(Meldung.BANKVERBINDUNG_EXISTIERT_NICHT);
		verify(bankverbindungRepository).finde(BANKVERBINDUNG_1.getId());
	}

	@Test
	@DisplayName("finden, wenn ID existiert")
	void test04()
	{
		when(bankverbindungRepository.finde(BANKVERBINDUNG_1.getId())).thenReturn(Option.of(BANKVERBINDUNG_1));
		when(bankverbindungMapping.mappe(BANKVERBINDUNG_1)).thenReturn(GESPEICHERTE_BANKVERBINDUNG_1);

		var ergebnis = sut.finde(BANKVERBINDUNG_1.getId().getWert().toString());

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BANKVERBINDUNG)).isEmpty(),
			() -> assertThat(ergebnis.getId()).isEqualTo(GESPEICHERTE_BANKVERBINDUNG_1.getId()),
			() -> assertThat(ergebnis.getIban()).isEqualTo(GESPEICHERTE_BANKVERBINDUNG_1.getIban()),
			() -> assertThat(ergebnis.getBank()).isEqualTo(GESPEICHERTE_BANKVERBINDUNG_1.getBank()));
		verify(bankverbindungRepository).finde(BANKVERBINDUNG_1.getId());
		verify(bankverbindungMapping).mappe(BANKVERBINDUNG_1);
	}

	@Test
	@DisplayName("leere Eingabedaten melden")
	void test05()
	{
		var ergebnis = sut.erstelle(new NeueBankverbindung(LEER, LEER));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.IBAN)).containsExactlyInAnyOrder(Meldung.IBAN_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BANK)).containsExactlyInAnyOrder(
				Meldung.idLeer(Schluessel.BANK)),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("ungültige Eingabedaten melden")
	void test06()
	{
		var ergebnis = sut.erstelle(new NeueBankverbindung(
			"DE87280200504008357801",
			"46c317ae-25dd-4805-98ca-273e45d32815-123"));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.IBAN)).containsExactlyInAnyOrder(Meldung.IBAN_UNGUELTIG),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BANK)).containsExactlyInAnyOrder(
				Meldung.idUngueltig(Schluessel.BANK)),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("bereits existierende IBAN und nicht existierende IDs melden")
	void test07()
	{
		when(bankverbindungRepository.istVorhanden(IBAN_1)).thenReturn(true);
		when(bankRepository.finde(BANK_1.getId())).thenReturn(Option.none());

		var ergebnis = sut.erstelle(new NeueBankverbindung(IBAN_1_WERT, BANK_1.getId().getWert().toString()));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.IBAN)).containsExactlyInAnyOrder(
				Meldung.IBAN_EXISTIERT_BEREITS),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BANK)).containsExactlyInAnyOrder(
				Meldung.BANK_EXISTIERT_NICHT),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
		verify(bankverbindungRepository).istVorhanden(IBAN_1);
		verify(bankRepository).finde(BANK_1.getId());
	}

	@Test
	@DisplayName("erstellen")
	void test08()
	{
		when(bankverbindungRepository.istVorhanden(IBAN_1)).thenReturn(false);
		when(bankRepository.finde(BANK_1.getId())).thenReturn(Option.of(BANK_1));

		var ergebnis = sut.erstelle(new NeueBankverbindung(IBAN_1_WERT, BANK_1.getId().getWert().toString()));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.IBAN)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BANK)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.BANKVERBINDUNG_ERSTELLT));
		verify(bankverbindungRepository).istVorhanden(IBAN_1);
		verify(bankRepository).finde(BANK_1.getId());
		verify(bankverbindungRepository).speichere(any(Bankverbindung.class));
	}
}
