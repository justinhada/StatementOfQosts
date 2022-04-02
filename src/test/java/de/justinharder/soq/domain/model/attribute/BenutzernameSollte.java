package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Benutzername sollte")
class BenutzernameSollte extends Testdaten
{
	private Benutzername sut;

	private Validation<Meldung, Benutzername> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Benutzername.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BENUTZERNAME_LEER));

		validierung = Benutzername.aus("");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BENUTZERNAME_LEER));

		validierung = Benutzername.aus(" ");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BENUTZERNAME_LEER));

		validierung = Benutzername.aus("             ");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BENUTZERNAME_LEER));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Benutzername.aus(HARDER_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(HARDER_WERT));

		validierung = Benutzername.aus(TIEMERDING_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(TIEMERDING_WERT));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(Benutzername.aus(HARDER_WERT).get()).hasToString(HARDER_WERT);
	}
}
