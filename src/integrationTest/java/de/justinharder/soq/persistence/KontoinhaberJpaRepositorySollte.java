package de.justinharder.soq.persistence;

import de.justinharder.soq.IntegrationTest;
import de.justinharder.soq.domain.model.Kontoinhaber;
import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
@DisplayName("KontoinhaberJpaRepository sollte")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class KontoinhaberJpaRepositorySollte extends IntegrationTest
{
	@Inject
	KontoinhaberJpaRepository sut;

	@Test
	@Order(1)
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichere(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.loesche(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeAlle(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null, bankverbindung1)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde(benutzer1, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.istVorhanden(null, bankverbindung1)),
			() -> assertThrows(NullPointerException.class, () -> sut.istVorhanden(benutzer1, null)));
	}

	@Test
	@Order(2)
	@DisplayName("alle finden")
	void test02()
	{
		assertAll(
			() -> assertThat(sut.findeAlle()).containsExactlyInAnyOrder(kontoinhaber1, kontoinhaber2),
			() -> assertThat(sut.findeAlle(bankverbindung1)).containsExactlyInAnyOrder(kontoinhaber1),
			() -> assertThat(sut.findeAlle(bankverbindung2)).containsExactlyInAnyOrder(kontoinhaber2));
	}

	@Test
	@Order(3)
	@DisplayName("finden")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.finde(kontoinhaber1.getId())).contains(kontoinhaber1),
			() -> assertThat(sut.finde(kontoinhaber2.getId())).contains(kontoinhaber2),
			() -> assertThat(sut.finde(ID.aus("f22396bf-a21b-4b0b-b5c2-798b130a24c1", Schluessel.KONTOINHABER)
				.get())).isEmpty(),
			() -> assertThat(sut.finde(benutzer1, bankverbindung1)).contains(kontoinhaber1),
			() -> assertThat(sut.finde(benutzer2, bankverbindung2)).contains(kontoinhaber2),
			() -> assertThat(sut.finde(benutzer1, bankverbindung2)).isEmpty(),
			() -> assertThat(sut.istVorhanden(benutzer1, bankverbindung1)).isTrue(),
			() -> assertThat(sut.istVorhanden(benutzer2, bankverbindung2)).isTrue(),
			() -> assertThat(sut.istVorhanden(benutzer1, bankverbindung2)).isFalse());
	}

	@Test
	@Order(4)
	@Transactional
	@DisplayName("speichern")
	void test04()
	{
		var kontoinhaber = Kontoinhaber.aus(benutzer1, bankverbindung2).get();
		sut.speichere(kontoinhaber);
		assertThat(sut.finde(kontoinhaber.getId())).contains(kontoinhaber);
	}

	@Test
	@Order(5)
	@Transactional
	@DisplayName("löschen")
	void test05()
	{
		var kontoinhaber = sut.finde(benutzer1, bankverbindung2).get();
		sut.loesche(kontoinhaber);
		assertThat(sut.finde(kontoinhaber.getId())).isEmpty();
	}
}
