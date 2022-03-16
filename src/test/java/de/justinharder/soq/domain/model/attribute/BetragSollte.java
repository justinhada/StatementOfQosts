package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Betrag sollte")
class BetragSollte extends Testdaten
{
	private Betrag sut;

	private Validation<Meldung, Betrag> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Betrag.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BETRAG_LEER));

		validierung = Betrag.aus(new BigDecimal("-1"));
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BETRAG_NEGATIV));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Betrag.aus(BigDecimal.ZERO);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(BigDecimal.ZERO));

		validierung = Betrag.aus(B_1_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(B_1_WERT));

		validierung = Betrag.aus(B_10_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(B_10_WERT));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(Betrag.aus(B_1_WERT).get()).hasToString("1");
	}
}
