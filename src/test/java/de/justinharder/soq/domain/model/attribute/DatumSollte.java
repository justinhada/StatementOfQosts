package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Datum sollte")
class DatumSollte extends Testdaten
{
	private Datum sut;

	private Validation<Meldung, Datum> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Datum.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.DATUM));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Datum.aus(D_01012020_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(D_01012020_WERT));

		validierung = Datum.aus(D_01012021_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(D_01012021_WERT));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(Datum.aus(D_01012020_WERT).get()).hasToString("01.01.2020");
	}
}
