package de.justinharder.soq.domain.services.imports.erzeugung;

import de.justinharder.ImportTestdaten;
import de.justinharder.soq.domain.repository.BankverbindungRepository;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("BankverbindungErzeugung sollte")
class BankverbindungErzeugungSollte extends ImportTestdaten
{
	private static final String NAME = "Laura Tiemerding";

	private BankverbindungErzeugung sut;

	private BankverbindungRepository bankverbindungRepository;
	private BankErzeugung bankErzeugung;

	@BeforeEach
	void setup()
	{
		bankverbindungRepository = mock(BankverbindungRepository.class);
		bankErzeugung = mock(BankErzeugung.class);

		sut = new BankverbindungErzeugung(bankverbindungRepository, bankErzeugung);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new BankverbindungErzeugung(null, bankErzeugung)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungErzeugung(bankverbindungRepository, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeOderErzeuge(null, BIC_1)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeOderErzeuge(IBAN_1, null)));
	}

	@Test
	@DisplayName("Bankverbindung finden und nicht neu erzeugen")
	void test02()
	{
		when(bankverbindungRepository.finde(IBAN_1)).thenReturn(Option.of(BANKVERBINDUNG_1));
		when(bankErzeugung.findeOderErzeuge(BIC_1)).thenReturn(Validation.valid(BANK_1));

		var ergebnis = sut.findeOderErzeuge(IBAN_1, BIC_1);
		assertAll(
			() -> assertThrows(RuntimeException.class, ergebnis::getError),
			() -> assertThat(ergebnis.get()).isEqualTo(BANKVERBINDUNG_1));
		verify(bankverbindungRepository).finde(IBAN_1);
	}

	@Test
	@DisplayName("Bankverbindung nicht finden und neu erzeugen")
	void test03()
	{
		when(bankverbindungRepository.finde(IBAN_1)).thenReturn(Option.none());
		when(bankErzeugung.findeOderErzeuge(BIC_1)).thenReturn(Validation.valid(BANK_1));

		var validierung = sut.findeOderErzeuge(IBAN_1, BIC_1);
		var ergebnis = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(ergebnis.getIban()).isEqualTo(IBAN_1),
			() -> assertThat(ergebnis.getBank()).isEqualTo(BANK_1));
		verify(bankverbindungRepository).finde(IBAN_1);
		verify(bankErzeugung).findeOderErzeuge(BIC_1);
	}
}
