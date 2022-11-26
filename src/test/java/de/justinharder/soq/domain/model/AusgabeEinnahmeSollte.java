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
class EinnahmeAusgabeSollte extends Testdaten
{
	private EinnahmeAusgabe sut;

	private Validation<Meldungen, EinnahmeAusgabe> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = EinnahmeAusgabe.aus(null, null, null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BEZEICHNUNG, Meldung.TURNUS,
				Meldung.BETRAG_LEER, Meldung.BENUTZER_LEER));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = EinnahmeAusgabe.aus(GEHALT, MONATLICH, B_1, JUSTIN_HARDER);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(GEHALT),
			() -> assertThat(sut.getTurnus()).isEqualTo(MONATLICH),
			() -> assertThat(sut.getBetrag()).isEqualTo(B_1),
			() -> assertThat(sut.getBenutzer()).isEqualTo(JUSTIN_HARDER));

		validierung = EinnahmeAusgabe.aus(WEIHNACHTSGELD, JAEHRLICH, B_10, LAURA_TIEMERDING);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(WEIHNACHTSGELD),
			() -> assertThat(sut.getTurnus()).isEqualTo(JAEHRLICH),
			() -> assertThat(sut.getBetrag()).isEqualTo(B_10),
			() -> assertThat(sut.getBenutzer()).isEqualTo(LAURA_TIEMERDING));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		validierung = EinnahmeAusgabe.aus(GEHALT, MONATLICH, B_1, JUSTIN_HARDER);
		sut = validierung.get();
		assertThat(sut).hasToString(
			"Einnahmequelle{ID=" + sut.getId() + ", Bezeichnung=Gehalt, Turnus=MONATLICH, Betrag=1, Benutzer=Benutzer{ID=" + sut.getBenutzer()
				.getId() + ", Nachname=Harder, Vorname=Justin}}");
	}

}
