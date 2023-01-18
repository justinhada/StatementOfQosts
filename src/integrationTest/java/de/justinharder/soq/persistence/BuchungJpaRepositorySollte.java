package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.attribute.ID;
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
@DisplayName("BuchungJpaRepository sollte")
class BuchungJpaRepositorySollte extends JpaRepositorySollte
{
	@Inject
	BuchungJpaRepository sut;

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
				sut.finde(ID.aus("bbc67b44-9f75-4a8c-9271-6533f3e062e3", Schluessel.BUCHUNG).get())).isNotEmpty(),
			() -> assertThat(
				sut.finde(ID.aus("f1b01d7c-8fc2-4367-ae1e-d4042aa4bd9e", Schluessel.BUCHUNG).get())).isNotEmpty(),
			() -> assertThat(
				sut.finde(ID.aus("f22396bf-a21b-4b0b-b5c2-798b130a24c1", Schluessel.BUCHUNG).get())).isEmpty());
	}

	@Test
	@Transactional
	@DisplayName("speichern")
	@Disabled("Wenn gespeichert wird, funktioniert ein ViewTest nicht mehr")
	void test04()
	{}

	@Test
	@Transactional
	@DisplayName("löschen")
	@Disabled("Wenn gelöscht wird, funktioniert ein ViewTest nicht mehr")
	void test05()
	{}
}
