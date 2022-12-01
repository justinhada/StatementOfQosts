package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ID sollte")
class IDSollte extends Testdaten
{
	@RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
	@DisplayName("unterschiedlich generiert werden")
	void test01()
	{
		assertThat(ID.random()).isNotEqualTo(ID.random());
	}
}
