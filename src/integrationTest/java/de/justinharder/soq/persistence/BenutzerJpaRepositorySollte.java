package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.model.attribute.Nachname;
import de.justinharder.soq.domain.model.attribute.Vorname;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
@DisplayName("BenutzerJpaRepository sollte")
class BenutzerJpaRepositorySollte
{
	@Inject
	BenutzerJpaRepository sut;

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichere(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.loesche(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		assertThat(sut.findeAlle()).isNotEmpty();
	}

	@Test
	@DisplayName("finden")
	void test03()
	{
		assertAll(
			() -> assertThat(
				sut.finde(ID.aus("1eaa1624-69f3-4634-a96f-a3a9fd9c7bb4", Schluessel.BENUTZER).get())).isNotEmpty(),
			() -> assertThat(
				sut.finde(ID.aus("c09e5a5d-96c7-4607-b053-e8091a9481a7", Schluessel.BENUTZER).get())).isNotEmpty(),
			() -> assertThat(
				sut.finde(ID.aus("f22396bf-a21b-4b0b-b5c2-798b130a24c1", Schluessel.BENUTZER).get())).isEmpty());
	}

	@Test
	@Transactional
	@DisplayName("speichern")
	@Disabled("Wenn gespeichert wird, funktioniert ein ViewTest nicht mehr")
	void test04()
	{
		var benutzer = Benutzer.aus(Nachname.aus("Harder").get(), Vorname.aus("Nicole").get()).get();
		sut.speichere(benutzer);
		assertThat(sut.finde(benutzer.getId())).isNotEmpty();
	}

	@Test
	@Transactional
	@DisplayName("löschen")
	@Disabled("Wenn gelöscht wird, funktioniert ein ViewTest nicht mehr")
	void test05()
	{
		var benutzer = sut.finde(ID.aus("1eaa1624-69f3-4634-a96f-a3a9fd9c7bb4", Schluessel.BENUTZER).get()).get();
		sut.loesche(benutzer);
		assertThat(sut.finde(benutzer.getId())).isEmpty();
	}
}
