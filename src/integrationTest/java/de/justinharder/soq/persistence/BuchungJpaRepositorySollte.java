package de.justinharder.soq.persistence;

import de.justinharder.soq.IntegrationTest;
import de.justinharder.soq.domain.model.Buchung;
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
@DisplayName("BuchungJpaRepository sollte")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BuchungJpaRepositorySollte extends IntegrationTest
{
	@Inject
	BuchungJpaRepository sut;

	@Test
	@Order(1)
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichere(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.loesche(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null, kategorie1)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde(umsatz1, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.istVorhanden(null, kategorie1)),
			() -> assertThrows(NullPointerException.class, () -> sut.istVorhanden(umsatz1, null)));
	}

	@Test
	@Order(2)
	@DisplayName("alle finden")
	void test02()
	{
		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(buchung1, buchung2);
	}

	@Test
	@Order(3)
	@DisplayName("finden")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.finde(buchung1.getId())).contains(buchung1),
			() -> assertThat(sut.finde(buchung2.getId())).contains(buchung2),
			() -> assertThat(sut.finde(ID.aus("f22396bf-a21b-4b0b-b5c2-798b130a24c1", Schluessel.BUCHUNG).get()))
				.isEmpty(),
			() -> assertThat(sut.finde(umsatz1, kategorie1)).contains(buchung1),
			() -> assertThat(sut.finde(umsatz2, kategorie2)).contains(buchung2),
			() -> assertThat(sut.finde(umsatz2, kategorie1)).isEmpty(),
			() -> assertThat(sut.istVorhanden(umsatz1, kategorie1)).isTrue(),
			() -> assertThat(sut.istVorhanden(umsatz2, kategorie2)).isTrue(),
			() -> assertThat(sut.istVorhanden(umsatz2, kategorie1)).isFalse());
	}

	@Test
	@Order(4)
	@Transactional
	@DisplayName("speichern")
	void test04()
	{
		var buchung = Buchung.aus(umsatz3, kategorie1).get();
		sut.speichere(buchung);
		assertThat(sut.finde(buchung.getId())).contains(buchung);
	}

	@Test
	@Order(5)
	@Transactional
	@DisplayName("löschen")
	void test05()
	{
		var buchung = sut.finde(umsatz3, kategorie1).get();
		sut.loesche(buchung);
		assertThat(sut.finde(buchung.getId())).isEmpty();
	}
}
