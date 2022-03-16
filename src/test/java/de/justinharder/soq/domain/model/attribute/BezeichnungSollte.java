package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Bezeichnung sollte")
class BezeichnungSollte extends Testdaten
{
	private Bezeichnung sut;

	private Validation<Meldung, Bezeichnung> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Bezeichnung.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BEZEICHNUNG));

		validierung = Bezeichnung.aus("");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BEZEICHNUNG));

		validierung = Bezeichnung.aus(" ");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BEZEICHNUNG));

		validierung = Bezeichnung.aus("             ");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BEZEICHNUNG));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Bezeichnung.aus(OHNE_ESSEN_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(OHNE_ESSEN_WERT));

		validierung = Bezeichnung.aus(NUR_ESSEN_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(NUR_ESSEN_WERT));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(Bezeichnung.aus(OHNE_ESSEN_WERT).get()).hasToString(OHNE_ESSEN_WERT);
	}
}
