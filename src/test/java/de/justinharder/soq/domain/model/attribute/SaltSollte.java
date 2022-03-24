package de.justinharder.soq.domain.model.attribute;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Salt sollte")
class SaltSollte
{
	@RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
	@DisplayName("unterschiedlich generiert werden")
	void test01()
	{
		assertThat(Salt.random()).isNotEqualTo(Salt.random());
	}
}
