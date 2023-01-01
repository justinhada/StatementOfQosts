package de.justinharder.soq.domain.services;

import de.justinharder.DTOTestdaten;
import de.justinharder.soq.domain.model.Bank;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BankRepository;
import de.justinharder.soq.domain.services.dto.NeueBank;
import de.justinharder.soq.domain.services.mapping.BankMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("BankService sollte")
class BankServiceSollte extends DTOTestdaten
{
	private BankService sut;

	private BankRepository bankRepository;
	private BankMapping bankMapping;

	@BeforeEach
	void setup()
	{
		bankRepository = mock(BankRepository.class);
		bankMapping = mock(BankMapping.class);

		sut = new BankService(bankRepository, bankMapping);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new BankService(bankRepository, null)),
			() -> assertThrows(NullPointerException.class, () -> new BankService(null, bankMapping)),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelle(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		when(bankRepository.findeAlle()).thenReturn(List.of(BANK_1, BANK_2));
		when(bankMapping.mappe(BANK_1)).thenReturn(GESPEICHERTE_BANK_1);
		when(bankMapping.mappe(BANK_2)).thenReturn(GESPEICHERTE_BANK_2);

		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(GESPEICHERTE_BANK_1, GESPEICHERTE_BANK_2);
		verify(bankRepository).findeAlle();
		verify(bankMapping).mappe(BANK_1);
		verify(bankMapping).mappe(BANK_2);
	}

	@Test
	@DisplayName("leere Eingabedaten melden")
	void test03()
	{
		var ergebnis = sut.erstelle(new NeueBank(LEER, LEER));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BEZEICHNUNG)).containsExactlyInAnyOrder(
				Meldung.BEZEICHNUNG_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BIC)).containsExactlyInAnyOrder(Meldung.BIC_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("ungültige BIC melden")
	void test04()
	{
		var ergebnis = sut.erstelle(new NeueBank(BEZEICHNUNG_1_WERT, "OLBODEH2XXXX"));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BEZEICHNUNG)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BIC)).containsExactlyInAnyOrder(Meldung.BIC_UNGUELTIG),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("bereits existierende Daten melden")
	void test05()
	{
		when(bankRepository.istVorhanden(BEZEICHNUNG_1)).thenReturn(true);
		when(bankRepository.istVorhanden(BIC_1)).thenReturn(true);

		var ergebnis = sut.erstelle(new NeueBank(BEZEICHNUNG_1_WERT, BIC_1_WERT));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BEZEICHNUNG)).containsExactlyInAnyOrder(
				Meldung.BEZEICHNUNG_EXISTIERT_BEREITS),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BIC)).containsExactlyInAnyOrder(
				Meldung.BIC_EXISTIERT_BEREITS),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
		verify(bankRepository).istVorhanden(BEZEICHNUNG_1);
		verify(bankRepository).istVorhanden(BIC_1);
	}

	@Test
	@DisplayName("erstellen")
	void test06()
	{
		when(bankRepository.istVorhanden(BEZEICHNUNG_1)).thenReturn(false);
		when(bankRepository.istVorhanden(BIC_1)).thenReturn(false);

		var ergebnis = sut.erstelle(new NeueBank(BEZEICHNUNG_1_WERT, BIC_1_WERT));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BEZEICHNUNG)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BIC)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.BANK_ERSTELLT));
		verify(bankRepository).istVorhanden(BEZEICHNUNG_1);
		verify(bankRepository).istVorhanden(BIC_1);
		verify(bankRepository).speichere(any(Bank.class));
	}
}
