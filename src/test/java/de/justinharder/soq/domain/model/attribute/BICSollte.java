package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BIC sollte")
class BICSollte extends Testdaten
{
	private BIC sut;

	private Validation<Meldung, BIC> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = BIC.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BIC));

		validierung = BIC.aus(LEER);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BIC));

		validierung = BIC.aus(LEER_KURZ);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BIC));

		validierung = BIC.aus(LEER_LANG);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BIC));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = BIC.aus(BIC_1_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(BIC_1_WERT));

		validierung = BIC.aus(BIC_2_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(BIC_2_WERT));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(BIC_1).hasToString(BIC_1_WERT);
	}
}