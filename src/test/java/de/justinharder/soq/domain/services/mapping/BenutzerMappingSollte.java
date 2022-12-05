package de.justinharder.soq.domain.services.mapping;

import de.justinharder.DtoTestdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BenutzerMapping sollte")
class BenutzerMappingSollte extends DtoTestdaten
{
	private BenutzerMapping sut;

	@BeforeEach
	void setup()
	{
		sut = new BenutzerMapping();
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
			() -> assertThat(sut.mappe(BENUTZER_1)).isEqualTo(GESPEICHERTER_BENUTZER_1),
			() -> assertThat(sut.mappe(BENUTZER_2)).isEqualTo(GESPEICHERTER_BENUTZER_2));
	}
}