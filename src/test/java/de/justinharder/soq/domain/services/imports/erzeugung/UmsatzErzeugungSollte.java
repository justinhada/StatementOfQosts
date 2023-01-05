package de.justinharder.soq.domain.services.imports.erzeugung;

import de.justinharder.ImportTestdaten;
import de.justinharder.soq.domain.model.Umsatz;
import de.justinharder.soq.domain.repository.UmsatzRepository;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("UmsatzErzeugung sollte")
class UmsatzErzeugungSollte extends ImportTestdaten
{
	private UmsatzErzeugung sut;

	private UmsatzRepository umsatzRepository;
	private BankverbindungErzeugung bankverbindungErzeugung;

	@BeforeEach
	void setup()
	{
		umsatzRepository = mock(UmsatzRepository.class);
		bankverbindungErzeugung = mock(BankverbindungErzeugung.class);

		sut = new UmsatzErzeugung(umsatzRepository, bankverbindungErzeugung);

		when(bankverbindungErzeugung.findeAuftraggeber(IBAN_1)).thenReturn(Validation.valid(BANKVERBINDUNG_1));
		when(bankverbindungErzeugung.findeOderErzeuge(IBAN_2, BIC_2)).thenReturn(Validation.valid(BANKVERBINDUNG_2));
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new UmsatzErzeugung(null, bankverbindungErzeugung)),
			() -> assertThrows(NullPointerException.class, () -> new UmsatzErzeugung(umsatzRepository, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeOderErzeuge(null)));
	}

	@Test
	@DisplayName("Umsatz finden und nicht neu erzeugen")
	void test02()
	{
		when(umsatzRepository.finde(
			UMSATZ_DATUM_DATUM,
			UMSATZ_DATUM_BETRAG,
			UMSATZ_DATUM_VERWENDUNGSZWECK,
			BANKVERBINDUNG_1,
			BANKVERBINDUNG_2)).thenReturn(Option.of(UMSATZ));

		var ergebnis = sut.findeOderErzeuge(UMSATZ_DATUM_AUS_OLB);
		assertAll(
			() -> assertThrows(RuntimeException.class, ergebnis::getError),
			() -> assertThat(ergebnis.get()).isEqualTo(UMSATZ));
		verify(umsatzRepository).finde(
			UMSATZ_DATUM_DATUM,
			UMSATZ_DATUM_BETRAG,
			UMSATZ_DATUM_VERWENDUNGSZWECK,
			BANKVERBINDUNG_1,
			BANKVERBINDUNG_2);
		verify(umsatzRepository).speichere(UMSATZ);
	}

	@Test
	@DisplayName("Umsatz nicht finden und neu erzeugen")
	void test03()
	{
		when(umsatzRepository.finde(
			UMSATZ_DATUM_DATUM,
			UMSATZ_DATUM_BETRAG,
			UMSATZ_DATUM_VERWENDUNGSZWECK,
			BANKVERBINDUNG_1,
			BANKVERBINDUNG_2)).thenReturn(Option.none());

		var validierung = sut.findeOderErzeuge(UMSATZ_DATUM_AUS_OLB);
		var ergebnis = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(ergebnis.getDatum()).isEqualTo(UMSATZ_DATUM_DATUM),
			() -> assertThat(ergebnis.getBetrag()).isEqualTo(UMSATZ_DATUM_BETRAG),
			() -> assertThat(ergebnis.getVerwendungszweck()).isEqualTo(UMSATZ_DATUM_VERWENDUNGSZWECK),
			() -> assertThat(ergebnis.getBankverbindungAuftraggeber()).isEqualTo(BANKVERBINDUNG_1),
			() -> assertThat(ergebnis.getBankverbindungZahlungsbeteiligter()).isEqualTo(BANKVERBINDUNG_2));
		verify(umsatzRepository).finde(
			UMSATZ_DATUM_DATUM,
			UMSATZ_DATUM_BETRAG,
			UMSATZ_DATUM_VERWENDUNGSZWECK,
			BANKVERBINDUNG_1,
			BANKVERBINDUNG_2);
		verify(umsatzRepository).speichere(any(Umsatz.class));
	}
}
