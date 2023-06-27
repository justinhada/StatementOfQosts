package de.justinharder.soq.domain.model;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.attribute.Turnus;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Vertrag sollte")
class VertragSollte extends Testdaten
{
	private Vertrag sut;
	private Validation<Meldungen, Vertrag> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Vertrag.aus(null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BEZEICHNUNG_LEER,
				Meldung.TURNUS));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Vertrag.aus(BEZEICHNUNG_5, Turnus.MONATLICH);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(BEZEICHNUNG_5));

		validierung = Vertrag.aus(BEZEICHNUNG_6, Turnus.JAEHRLICH);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(BEZEICHNUNG_6));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(VERTRAG_1).hasToString(
			"Vertrag{ID=" + VERTRAG_1.getId() + ", Bezeichnung=Wohnungsmiete, Turnus=monatlich, Buchungen=[]}");
	}

	@Test
	@DisplayName("Buchung hinzufügen")
	void test04()
	{
		validierung = Vertrag.aus(BEZEICHNUNG_5, Turnus.MONATLICH);
		sut = validierung.get();

		sut.fuegeBuchungHinzu(BUCHUNG_1);

		assertAll(
			() -> assertThat(sut.getBuchungen()).containsExactlyInAnyOrder(BUCHUNG_1),
			() -> assertThrows(NullPointerException.class, () -> sut.fuegeBuchungHinzu(null)));
	}

	@Test
	@DisplayName("null validieren")
	void test05()
	{
		assertThrows(NullPointerException.class, () -> sut.fuegeBuchungHinzu(null));
	}
}
