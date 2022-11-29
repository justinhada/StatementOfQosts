package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Verwendungszweck sollte")
class VerwendungszweckSollte extends Testdaten
{
	private Verwendungszweck sut;

	private Validation<Meldungen, Verwendungszweck> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Verwendungszweck.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.VERWENDUNGSZWECK));

		validierung = Verwendungszweck.aus(LEER);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.VERWENDUNGSZWECK));

		validierung = Verwendungszweck.aus(LEER_KURZ);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.VERWENDUNGSZWECK));

		validierung = Verwendungszweck.aus(LEER_LANG);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.VERWENDUNGSZWECK));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Verwendungszweck.aus(VERWENDUNGSZWECK_1_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(VERWENDUNGSZWECK_1_WERT));

		validierung = Verwendungszweck.aus(VERWENDUNGSZWECK_2_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(VERWENDUNGSZWECK_2_WERT));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(VERWENDUNGSZWECK_1).hasToString(VERWENDUNGSZWECK_1_WERT);
	}
}
