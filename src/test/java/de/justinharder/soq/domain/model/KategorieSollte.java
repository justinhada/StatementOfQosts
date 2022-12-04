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

@DisplayName("Kategorie sollte")
class KategorieSollte extends Testdaten
{
	private Kategorie sut;

	private Validation<Meldungen, Kategorie> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Kategorie.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BEZEICHNUNG_LEER));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Kategorie.aus(BEZEICHNUNG_3);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(BEZEICHNUNG_3));

		validierung = Kategorie.aus(BEZEICHNUNG_4);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(BEZEICHNUNG_4));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(KATEGORIE_1).hasToString("Kategorie{ID=" + KATEGORIE_1.getId() + ", Bezeichnung=Lebensmittel}");
	}
}
