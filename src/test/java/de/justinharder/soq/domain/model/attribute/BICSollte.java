package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BIC sollte")
class BICSollte extends Testdaten
{
	private BIC sut;

	private Validation<Meldung, BIC> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = BIC.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BIC));

		validierung = BIC.aus("");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BIC));

		validierung = BIC.aus(" ");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BIC));

		validierung = BIC.aus("             ");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BIC));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = BIC.aus("GENODEF1DIK");
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo("GENODEF1DIK"));

		validierung = BIC.aus("OLBODEH2XXX");
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo("OLBODEH2XXX"));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(BIC.aus("GENODEF1DIK").get()).hasToString("GENODEF1DIK");
	}
}
