package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Turnus sollte")
class TurnusSollte extends Testdaten
{
	@Test
	@DisplayName("Monate besitzen")
	void test01()
	{
		assertAll(
			() -> assertThat(Turnus.MONATLICH.getMonate()).isEqualTo(1),
			() -> assertThat(Turnus.QUARTALSWEISE.getMonate()).isEqualTo(3),
			() -> assertThat(Turnus.HALBJAEHRLICH.getMonate()).isEqualTo(6),
			() -> assertThat(Turnus.JAEHRLICH.getMonate()).isEqualTo(12));
	}

	@Test
	@DisplayName("sich drucken")
	void test02()
	{
		assertAll(
			() -> assertThat(Turnus.MONATLICH).hasToString("monatlich"),
			() -> assertThat(Turnus.QUARTALSWEISE).hasToString("quartalsweise"),
			() -> assertThat(Turnus.HALBJAEHRLICH).hasToString("halbjährlich"),
			() -> assertThat(Turnus.JAEHRLICH).hasToString("jährlich"));
	}
}
