package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Betrag sollte")
class BetragSollte extends Testdaten
{
	private Betrag sut;

	private Validation<Meldungen, Betrag> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Betrag.aus((BigDecimal) null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BETRAG_LEER));

		validierung = Betrag.aus((String) null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BETRAG_LEER));

		validierung = Betrag.aus(LEER);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BETRAG_LEER));

		validierung = Betrag.aus(LEER_KURZ);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BETRAG_LEER));

		validierung = Betrag.aus(LEER_LANG);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BETRAG_LEER));

		validierung = Betrag.aus("1.0.0");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BETRAG_UNGUELTIG));

		validierung = Betrag.aus("1,0,0");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BETRAG_UNGUELTIG));

		validierung = Betrag.aus("1.,0");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BETRAG_UNGUELTIG));

		validierung = Betrag.aus("1.0,0");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BETRAG_UNGUELTIG));

		validierung = Betrag.aus("1.00,0");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BETRAG_UNGUELTIG));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Betrag.aus(BigDecimal.ZERO);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)));

		validierung = Betrag.aus(BETRAG_1_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(BETRAG_1_WERT));

		validierung = Betrag.aus(BETRAG_2_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(BETRAG_2_WERT));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertAll(
			() -> assertThat(BETRAG_1).hasToString(BETRAG_1_WERT.toString().replace(".", ",")),
			() -> assertThat(Betrag.aus("0").get()).hasToString("0,00"));
	}
}
