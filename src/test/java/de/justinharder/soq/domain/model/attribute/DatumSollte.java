package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Datum sollte")
class DatumSollte extends Testdaten
{
	private Datum sut;

	private Validation<Meldungen, Datum> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Datum.aus((LocalDate) null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.DATUM_LEER));

		validierung = Datum.aus((String) null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.DATUM_LEER));

		validierung = Datum.aus(LEER);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.DATUM_LEER));

		validierung = Datum.aus(LEER_KURZ);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.DATUM_LEER));

		validierung = Datum.aus(LEER_LANG);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.DATUM_LEER));

		validierung = Datum.aus("01.01.2020");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.DATUM_UNGUELTIG));

		validierung = Datum.aus("2020-13-01");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.DATUM_UNGUELTIG));

		validierung = Datum.aus("29.02.2023");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.DATUM_UNGUELTIG));

		validierung = Datum.aus("2023-02-29");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.DATUM_UNGUELTIG));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Datum.aus(DATUM_1_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(DATUM_1_WERT));

		validierung = Datum.aus(DATUM_2_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(DATUM_2_WERT));

		validierung = Datum.aus(DATUM_1_WERT.format(DateTimeFormatter.ISO_DATE));
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(DATUM_1_WERT));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(DATUM_1).hasToString("01.01.2020");
	}
}
