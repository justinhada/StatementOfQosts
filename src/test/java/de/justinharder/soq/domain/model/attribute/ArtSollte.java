package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
			() -> assertThat(Art.aus(Betrag.aus(new BigDecimal(-1)).get())).isEqualTo(Art.AUSGABE),
			() -> assertThat(Art.aus(B_1)).isEqualTo(Art.EINNAHME));
	}
}
