package de.justinharder.soq.domain.services.mapping;

import de.justinharder.DtoTestdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("KategorieMapping sollte")
class KategorieMappingSollte extends DtoTestdaten
{
	private KategorieMapping sut;

	@BeforeEach
	void setup()
	{
		sut = new KategorieMapping();
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
			() -> assertThat(sut.mappe(KATEGORIE_1)).isEqualTo(GESPEICHERTE_KATEGORIE_1),
			() -> assertThat(sut.mappe(KATEGORIE_2)).isEqualTo(GESPEICHERTE_KATEGORIE_2));
	}
}
