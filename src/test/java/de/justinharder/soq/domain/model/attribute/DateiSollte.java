package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Datei sollte")
class DateiSollte extends Testdaten
{
	private Datei sut;

	private Validation<Meldungen, Datei> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Datei.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.DATEI));

		validierung = Datei.aus(LEER.getBytes(StandardCharsets.UTF_8));
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.DATEI));

		validierung = Datei.aus(LEER_KURZ.getBytes(StandardCharsets.UTF_8));
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.DATEI));

		validierung = Datei.aus(LEER_LANG.getBytes(StandardCharsets.UTF_8));
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.DATEI));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Datei.aus(DATEI_1_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(DATEI_1_WERT));

		validierung = Datei.aus(DATEI_2_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(DATEI_2_WERT));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(DATEI_1).hasToString(new String(DATEI_1_WERT, StandardCharsets.UTF_8));
	}
}
