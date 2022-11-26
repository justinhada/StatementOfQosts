package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("IBAN sollte")
class IBANSollte extends Testdaten
{
	private IBAN sut;

	private Validation<Meldung, IBAN> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = IBAN.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.IBAN));

		validierung = IBAN.aus(LEER);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.IBAN));

		validierung = IBAN.aus(LEER_KURZ);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.IBAN));

		validierung = IBAN.aus(LEER_LANG);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.IBAN));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = IBAN.aus(IBAN_1_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(IBAN_1_WERT));

		validierung = IBAN.aus(IBAN_2_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(IBAN_2_WERT));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(IBAN_1).hasToString(IBAN_1_WERT);
	}
}
