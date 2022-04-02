package de.justinharder.soq.domain.model;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Kostenpunkt sollte")
class KostenpunktSollte extends Testdaten
{
	private Kostenpunkt sut;

	private Validation<Meldungen, Kostenpunkt> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Kostenpunkt.aus(null, null, null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BEZEICHNUNG, Meldung.DATUM,
				Meldung.BETRAG_LEER, Meldung.PERSON_LEER));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Kostenpunkt.aus(EDEKA, D_01012020, B_1, JUSTIN_HARDER);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(EDEKA),
			() -> assertThat(sut.getDatum()).isEqualTo(D_01012020),
			() -> assertThat(sut.getBetrag()).isEqualTo(B_1),
			() -> assertThat(sut.getPerson()).isEqualTo(JUSTIN_HARDER));

		validierung = Kostenpunkt.aus(LIDL, D_01012021, B_10, LAURA_TIEMERDING);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(LIDL),
			() -> assertThat(sut.getDatum()).isEqualTo(D_01012021),
			() -> assertThat(sut.getBetrag()).isEqualTo(B_10),
			() -> assertThat(sut.getPerson()).isEqualTo(LAURA_TIEMERDING));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(Kostenpunkt.aus(EDEKA, D_01012020, B_1, JUSTIN_HARDER).get()).hasToString(
			"Kostenpunkt{Bezeichnung=Edeka, Datum=01.01.2020, Betrag=1, Person=Person{Nachname=Harder, Vorname=Justin}}");
	}
}
