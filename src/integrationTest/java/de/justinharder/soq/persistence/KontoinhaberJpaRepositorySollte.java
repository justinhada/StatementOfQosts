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
@DisplayName("KontoinhaberJpaRepository sollte")
class KontoinhaberJpaRepositorySollte
{
	@Inject
	KontoinhaberJpaRepository sut;

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
			() -> assertThat(sut.finde(
				ID.aus("bc01e451-051b-4bfa-979f-2acff87ba5a2", Schluessel.BANKVERBINDUNG).get())).isNotEmpty(),
			() -> assertThat(sut.finde(
				ID.aus("7f94d5e5-3972-4f44-b81b-994a244ba3ad", Schluessel.BANKVERBINDUNG).get())).isNotEmpty(),
			() -> assertThat(
				sut.finde(ID.aus("f22396bf-a21b-4b0b-b5c2-798b130a24c1", Schluessel.BANKVERBINDUNG).get())).isEmpty());
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
