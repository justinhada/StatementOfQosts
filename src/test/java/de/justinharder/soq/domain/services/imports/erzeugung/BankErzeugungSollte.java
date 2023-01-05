package de.justinharder.soq.domain.services.imports.erzeugung;

import de.justinharder.ImportTestdaten;
import de.justinharder.soq.domain.model.Bank;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.repository.BankRepository;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("BankErzeugung sollte")
class BankErzeugungSollte extends ImportTestdaten
{
	private BankErzeugung sut;

	private BankRepository bankRepository;

	@BeforeEach
	void setup()
	{
		bankRepository = mock(BankRepository.class);

		sut = new BankErzeugung(bankRepository);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new BankErzeugung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeOderErzeuge(null)));
	}

	@Test
	@DisplayName("Bank finden und nicht neu erzeugen")
	void test02()
	{
		when(bankRepository.finde(BIC_1)).thenReturn(Option.of(BANK_1));

		var ergebnis = sut.findeOderErzeuge(BIC_1);
		assertAll(
			() -> assertThrows(RuntimeException.class, ergebnis::getError),
			() -> assertThat(ergebnis.get()).isEqualTo(BANK_1));
		verify(bankRepository).finde(BIC_1);
		verify(bankRepository).speichere(BANK_1);
	}

	@Test
	@DisplayName("Bank nicht finden und neu erzeugen")
	void test03()
	{
		when(bankRepository.finde(BIC_1)).thenReturn(Option.none());

		var validierung = sut.findeOderErzeuge(BIC_1);
		var ergebnis = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(ergebnis.getBezeichnung()).isEqualTo(Bezeichnung.aus("OLBO").get()),
			() -> assertThat(ergebnis.getBic()).isEqualTo(BIC_1));
		verify(bankRepository).finde(BIC_1);
		verify(bankRepository).speichere(any(Bank.class));
	}
}
