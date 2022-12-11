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

@DisplayName("Firma sollte")
class FirmaSollte extends Testdaten
{
	private Firma sut;

	private Validation<Meldungen, Firma> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Firma.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.FIRMA_LEER));

		validierung = Firma.aus(LEER);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.FIRMA_LEER));

		validierung = Firma.aus(LEER_KURZ);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.FIRMA_LEER));

		validierung = Firma.aus(LEER_LANG);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.FIRMA_LEER));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Firma.aus(FIRMA_1_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(FIRMA_1_WERT));

		validierung = Firma.aus(FIRMA_2_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(FIRMA_2_WERT));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(FIRMA_1).hasToString(FIRMA_1_WERT);
	}
}
