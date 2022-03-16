package de.justinharder.soq.domain.model.attribute;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ID sollte")
class IDSollte
{
	@RepeatedTest(value = 3, name = RepeatedTest.LONG_DISPLAY_NAME)
	@DisplayName("unterschiedlich generiert werden")
	void test01()
	{
		assertThat(ID.random()).isNotEqualTo(ID.random());
	}
}
