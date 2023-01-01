package de.justinharder.soq.domain.services.mapping;

import de.justinharder.DTOTestdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("PrivatpersonMapping sollte")
class PrivatpersonMappingSollte extends DTOTestdaten
{
	private PrivatpersonMapping sut;

	@BeforeEach
	void setup()
	{
		sut = new PrivatpersonMapping();
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
			() -> assertThat(sut.mappe(BENUTZER_1)).isEqualTo(GESPEICHERTE_PRIVATPERSON_1),
			() -> assertThat(sut.mappe(BENUTZER_2)).isEqualTo(GESPEICHERTE_PRIVATPERSON_2));
	}
}
