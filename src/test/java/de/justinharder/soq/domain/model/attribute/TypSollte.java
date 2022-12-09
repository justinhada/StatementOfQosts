package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Typ sollte")
class TypSollte extends Testdaten
{
	@Test
	@DisplayName("sich aus Betrag ermitteln")
	void test01()
	{
		assertAll(
			() -> assertThat(Typ.aus(BETRAG_2)).isEqualTo(Typ.AUSGABE),
			() -> assertThat(Typ.aus(BETRAG_1)).isEqualTo(Typ.EINNAHME));
	}
}
