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
		validierung = Benutzer.aus(HARDER, JUSTIN);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getNachname()).isEqualTo(HARDER),
			() -> assertThat(sut.getVorname()).isEqualTo(JUSTIN));

		validierung = Benutzer.aus(TIEMERDING, LAURA);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getNachname()).isEqualTo(TIEMERDING),
			() -> assertThat(sut.getVorname()).isEqualTo(LAURA));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		validierung = Benutzer.aus(HARDER, JUSTIN);
		sut = validierung.get();
		assertThat(sut).hasToString("Benutzer{ID=" + sut.getId() + ", Nachname=Harder, Vorname=Justin}");
	}
}
