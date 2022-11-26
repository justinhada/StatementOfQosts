package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("IBAN sollte")
class IBANSollte
{
	private IBAN sut;

	private Validation<Meldung, IBAN> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = IBAN.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.IBAN));

		validierung = IBAN.aus("");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.IBAN));

		validierung = IBAN.aus(" ");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.IBAN));

		validierung = IBAN.aus("             ");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.IBAN));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = IBAN.aus("DE87280200504008357800");
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo("DE87280200504008357800"));

		validierung = IBAN.aus("DE28280651080012888000");
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo("DE28280651080012888000"));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(IBAN.aus("DE87280200504008357800").get()).hasToString("DE87280200504008357800");
	}
}
