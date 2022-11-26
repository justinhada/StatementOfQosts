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

@DisplayName("Person sollte")
class BenutzerSollte extends Testdaten
{
	private Benutzer sut;

	private Validation<Meldungen, Benutzer> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Benutzer.aus(null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.NACHNAME, Meldung.VORNAME));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Benutzer.aus(NACHNAME_1, VORNAME_1);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getNachname()).isEqualTo(NACHNAME_1),
			() -> assertThat(sut.getVorname()).isEqualTo(VORNAME_1));

		validierung = Benutzer.aus(NACHNAME_2, VORNAME_2);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getNachname()).isEqualTo(NACHNAME_2),
			() -> assertThat(sut.getVorname()).isEqualTo(VORNAME_2));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(BENUTZER_1).hasToString("Benutzer{ID=" + BENUTZER_1.getId() + ", Nachname=Harder, Vorname=Justin}");
	}
}
