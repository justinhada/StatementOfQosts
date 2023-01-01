package de.justinharder.soq.domain.services.mapping;

import de.justinharder.DTOTestdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BankMapping sollte")
class BankMappingSollte extends DTOTestdaten
{
	private BankMapping sut;

	@BeforeEach
	void setup()
	{
		sut = new BankMapping();
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertThrows(NullPointerException.class, () -> sut.mappe(null));
	}

	@Test
	@DisplayName("mappen")
	void test02()
	{
		assertAll(
			() -> assertThat(sut.mappe(BANK_1)).isEqualTo(GESPEICHERTE_BANK_1),
			() -> assertThat(sut.mappe(BANK_2)).isEqualTo(GESPEICHERTE_BANK_2));
	}
}
