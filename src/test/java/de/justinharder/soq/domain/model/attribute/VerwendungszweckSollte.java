package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Verwendungszweck sollte")
class VerwendungszweckSollte extends Testdaten
{
	private Verwendungszweck sut;

	private Validation<Meldung, Verwendungszweck> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Verwendungszweck.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.VERWENDUNGSZWECK));

		validierung = Verwendungszweck.aus("");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.VERWENDUNGSZWECK));

		validierung = Verwendungszweck.aus(" ");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.VERWENDUNGSZWECK));

		validierung = Verwendungszweck.aus("             ");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.VERWENDUNGSZWECK));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Verwendungszweck.aus("Wohnungsmiete");
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo("Wohnungsmiete"));

		validierung = Verwendungszweck.aus("Lohn/Gehalt");
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo("Lohn/Gehalt"));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(Verwendungszweck.aus("Wohnungsmiete").get()).hasToString("Wohnungsmiete");
	}
}
