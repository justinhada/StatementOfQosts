package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
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

	private Validation<Meldung, Nachname> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Nachname.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.NACHNAME));

		validierung = Nachname.aus("");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.NACHNAME));

		validierung = Nachname.aus(" ");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.NACHNAME));

		validierung = Nachname.aus("             ");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.NACHNAME));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Nachname.aus(HARDER_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(HARDER_WERT));

		validierung = Nachname.aus(TIEMERDING_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(TIEMERDING_WERT));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(Nachname.aus(HARDER_WERT).get()).hasToString(HARDER_WERT);
	}
}
