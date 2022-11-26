package de.justinharder.soq.domain.model;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
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

	private Validation<Meldung, Kategorie> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Kategorie.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.BEZEICHNUNG));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Kategorie.aus(BEZEICHNUNG_5);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(BEZEICHNUNG_5));

		validierung = Kategorie.aus(BEZEICHNUNG_6);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(BEZEICHNUNG_6));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(KATEGORIE_1).hasToString("Kategorie{ID=" + KATEGORIE_1.getId() + ", Bezeichnung=Lebensmittel}");
	}
}
