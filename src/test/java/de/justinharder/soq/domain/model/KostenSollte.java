package de.justinharder.soq.domain.model;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Kosten sollte")
class KostenSollte extends Testdaten
{
	private Kosten sut;

	private Validation<Meldung, Kosten> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Kosten.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BEZEICHNUNG));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Kosten.aus(OHNE_ESSEN);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(OHNE_ESSEN));

		validierung = Kosten.aus(NUR_ESSEN);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(NUR_ESSEN));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		sut = Kosten.aus(NUR_ESSEN).get();
		sut.fuegeKostenpunktHinzu(K_1);
		sut.fuegeKostenpunktHinzu(K_2);

		assertThat(sut).hasToString(
			"Kosten{Bezeichnung=Variable Kosten (nur Essen), Kostenpunkte=[Kostenpunkt{Bezeichnung=Edeka, Datum=01.01.2020, Betrag=1, Person=Person{Nachname=Harder, Vorname=Justin}}, Kostenpunkt{Bezeichnung=Lidl, Datum=01.01.2021, Betrag=10, Person=Person{Nachname=Tiemerding, Vorname=Laura}}]}");
	}

	@Test
	@DisplayName("Kostenpunkte besitzen")
	void test04()
	{
		sut = Kosten.aus(NUR_ESSEN).get();
		sut.fuegeKostenpunktHinzu(K_1);
		sut.fuegeKostenpunktHinzu(K_2);

		assertThat(sut.getKostenpunkte()).containsExactlyInAnyOrder(K_1, K_2);
	}
}
