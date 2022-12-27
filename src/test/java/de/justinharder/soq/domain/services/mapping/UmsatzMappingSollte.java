package de.justinharder.soq.domain.services.mapping;

import de.justinharder.DtoTestdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("UmsatzMapping sollte")
class UmsatzMappingSollte extends DtoTestdaten
{
	private UmsatzMapping sut;

	@BeforeEach
	void setup()
	{
		sut = new UmsatzMapping();
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
			() -> assertThat(sut.mappe(UMSATZ_1)).isEqualTo(GESPEICHERTER_UMSATZ_1),
			() -> assertThat(sut.mappe(UMSATZ_2)).isEqualTo(GESPEICHERTER_UMSATZ_2));
	}
}
