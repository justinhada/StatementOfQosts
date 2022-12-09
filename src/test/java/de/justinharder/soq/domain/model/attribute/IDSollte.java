package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("ID sollte")
class IDSollte extends Testdaten
{
	private static final Schluessel SCHLUESSEL = Schluessel.BENUTZER;
	private static final Meldung MELDUNG_ID_LEER = Meldung.idLeer(SCHLUESSEL);
	private static final Meldung MELDUNG_ID_UNGUELTIG = Meldung.idUngueltig(SCHLUESSEL);
	private ID sut;

	private Validation<Meldungen, ID> validierung;

	@RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
	@DisplayName("unterschiedlich generiert werden")
	void test01()
	{
		assertThat(ID.random()).isNotEqualTo(ID.random());
	}

	@Test
	@DisplayName("invalide sein")
	void test02()
	{
		validierung = ID.aus(null, SCHLUESSEL);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(MELDUNG_ID_LEER));

		validierung = ID.aus(LEER, SCHLUESSEL);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(MELDUNG_ID_LEER));

		validierung = ID.aus(LEER_KURZ, SCHLUESSEL);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(MELDUNG_ID_LEER));

		validierung = ID.aus(LEER_LANG, SCHLUESSEL);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(MELDUNG_ID_LEER));

		validierung = ID.aus("46c317ae", SCHLUESSEL);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(MELDUNG_ID_UNGUELTIG));

		validierung = ID.aus("46c317ae-25dd-4805-98ca-273e45d3281-aed2eda", SCHLUESSEL);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(MELDUNG_ID_UNGUELTIG));
	}

	@Test
	@DisplayName("valide sein")
	void test03()
	{
		validierung = ID.aus(ID_1_WERT, SCHLUESSEL);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(UUID.fromString(ID_1_WERT)));
	}

	@Test
	@DisplayName("sich drucken")
	void test04()
	{
		assertThat(ID_1).hasToString(ID_1_WERT);
	}
}
