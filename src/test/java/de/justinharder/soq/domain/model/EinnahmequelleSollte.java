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

@DisplayName("Einnahmequelle sollte")
class EinnahmequelleSollte extends Testdaten
{
	private Einnahmequelle sut;

	private Validation<Meldungen, Einnahmequelle> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Einnahmequelle.aus(null, null, null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BEZEICHNUNG, Meldung.TURNUS,
				Meldung.BETRAG_LEER, Meldung.PERSON_LEER));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Einnahmequelle.aus(GEHALT, MONATLICH, B_1, JUSTIN_HARDER);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(GEHALT),
			() -> assertThat(sut.getTurnus()).isEqualTo(MONATLICH),
			() -> assertThat(sut.getBetrag()).isEqualTo(B_1),
			() -> assertThat(sut.getPerson()).isEqualTo(JUSTIN_HARDER));

		validierung = Einnahmequelle.aus(WEIHNACHTSGELD, JAEHRLICH, B_10, LAURA_TIEMERDING);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(WEIHNACHTSGELD),
			() -> assertThat(sut.getTurnus()).isEqualTo(JAEHRLICH),
			() -> assertThat(sut.getBetrag()).isEqualTo(B_10),
			() -> assertThat(sut.getPerson()).isEqualTo(LAURA_TIEMERDING));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(Einnahmequelle.aus(GEHALT, MONATLICH, B_1, JUSTIN_HARDER).get()).hasToString(
			"Einnahmequelle{Bezeichnung=Gehalt, Turnus=MONATLICH, Betrag=1, Person=Person{Nachname=Harder, Vorname=Justin}}");
	}

}
