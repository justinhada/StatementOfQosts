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

@DisplayName("Nachname sollte")
class NachnameSollte extends Testdaten
{
	private Nachname sut;

	private Validation<Meldungen, Nachname> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Nachname.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.NACHNAME_LEER));

		validierung = Nachname.aus(LEER);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.NACHNAME_LEER));

		validierung = Nachname.aus(LEER_KURZ);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.NACHNAME_LEER));

		validierung = Nachname.aus(LEER_LANG);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.NACHNAME_LEER));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Nachname.aus(NACHNAME_1_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(NACHNAME_1_WERT));

		validierung = Nachname.aus(NACHNAME_2_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(NACHNAME_2_WERT));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(NACHNAME_1).hasToString(NACHNAME_1_WERT);
	}
}
