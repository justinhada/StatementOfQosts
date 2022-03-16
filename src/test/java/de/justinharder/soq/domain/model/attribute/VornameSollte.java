package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Vorname sollte")
class VornameSollte extends Testdaten
{
	private Vorname sut;

	private Validation<Meldung, Vorname> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Vorname.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.VORNAME));

		validierung = Vorname.aus("");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.VORNAME));

		validierung = Vorname.aus(" ");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.VORNAME));

		validierung = Vorname.aus("             ");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.VORNAME));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Vorname.aus(JUSTIN_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(JUSTIN_WERT));

		validierung = Vorname.aus(LAURA_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(LAURA_WERT));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(Vorname.aus(JUSTIN_WERT).get()).hasToString(JUSTIN_WERT);
	}
}
