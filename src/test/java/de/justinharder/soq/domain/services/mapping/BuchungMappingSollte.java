package de.justinharder.soq.domain.services.mapping;

import de.justinharder.DTOTestdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BuchungMapping sollte")
class BuchungMappingSollte extends DTOTestdaten
{
	private BuchungMapping sut;

	@BeforeEach
	void setup()
	{
		sut = new BuchungMapping();
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
			() -> assertThat(sut.mappe(BUCHUNG_1)).isEqualTo(GESPEICHERTE_BUCHUNG_1),
			() -> assertThat(sut.mappe(BUCHUNG_2)).isEqualTo(GESPEICHERTE_BUCHUNG_2));
	}
}
