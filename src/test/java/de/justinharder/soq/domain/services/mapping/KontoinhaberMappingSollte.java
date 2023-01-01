package de.justinharder.soq.domain.services.mapping;

import de.justinharder.DTOTestdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("KontoinhaberMapping sollte")
class KontoinhaberMappingSollte extends DTOTestdaten
{
	private KontoinhaberMapping sut;

	@BeforeEach
	void setup()
	{
		sut = new KontoinhaberMapping();
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
			() -> assertThat(sut.mappe(KONTOINHABER_1)).isEqualTo(GESPEICHERTER_KONTOINHABER_1),
			() -> assertThat(sut.mappe(KONTOINHABER_2)).isEqualTo(GESPEICHERTER_KONTOINHABER_2));
	}
}
