package de.justinharder.soq.domain.services.mapping;

import de.justinharder.DTOTestdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BankverbindungMapping sollte")
class BankverbindungMappingSollte extends DTOTestdaten
{
	private BankverbindungMapping sut;

	@BeforeEach
	void setup()
	{
		sut = new BankverbindungMapping();
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
			() -> assertThat(sut.mappe(BANKVERBINDUNG_1)).isEqualTo(GESPEICHERTE_BANKVERBINDUNG_1),
			() -> assertThat(sut.mappe(BANKVERBINDUNG_2)).isEqualTo(GESPEICHERTE_BANKVERBINDUNG_2));
	}
}
