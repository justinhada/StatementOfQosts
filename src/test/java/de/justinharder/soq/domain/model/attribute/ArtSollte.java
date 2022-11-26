package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Art sollte")
class ArtSollte extends Testdaten
{
	@Test
	@DisplayName("sich aus Betrag ermitteln")
	void test01()
	{
		assertAll(
			() -> assertThat(Art.aus(BETRAG_2)).isEqualTo(Art.AUSGABE),
			() -> assertThat(Art.aus(BETRAG_1)).isEqualTo(Art.EINNAHME));
	}
}
