package de.justinharder.soq.persistence;

import de.justinharder.soq.IntegrationTest;
import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.attribute.Firma;
import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.model.attribute.Nachname;
import de.justinharder.soq.domain.model.attribute.Vorname;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
@DisplayName("BenutzerJpaRepository sollte")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BenutzerJpaRepositorySollte extends IntegrationTest
{
	@Inject
	BenutzerJpaRepository sut;

	@Test
	@Order(1)
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.finde((ID) null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichere(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.loesche(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null, benutzer1.getVorname())),
			() -> assertThrows(NullPointerException.class, () -> sut.finde(benutzer1.getNachname(), null)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde((Firma) null)),
			() -> assertThrows(NullPointerException.class, () -> sut.istVorhanden(null, benutzer1.getVorname())),
			() -> assertThrows(NullPointerException.class, () -> sut.istVorhanden(benutzer1.getNachname(), null)),
			() -> assertThrows(NullPointerException.class, () -> sut.istVorhanden(null)));
	}

	@Test
	@Order(2)
	@DisplayName("alle finden")
	void test02()
	{
		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(benutzer1, benutzer2, benutzer3, benutzer4);
	}

	@Test
	@Order(3)
	@DisplayName("finden")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.finde(benutzer1.getId())).contains(benutzer1),
			() -> assertThat(sut.finde(benutzer2.getId())).contains(benutzer2),
			() -> assertThat(sut.finde(ID.aus("f22396bf-a21b-4b0b-b5c2-798b130a24c1", Schluessel.BENUTZER).get()))
				.isEmpty(),
			() -> assertThat(sut.finde(benutzer1.getNachname(), benutzer1.getVorname())).contains(benutzer1),
			() -> assertThat(sut.finde(benutzer1.getNachname(), Vorname.aus("Nicole").get())).isEmpty(),
			() -> assertThat(sut.finde(benutzer3.getFirma())).contains(benutzer3),
			() -> assertThat(sut.finde(Firma.aus("Lidl Stiftung & Co. KG").get())).isEmpty(),
			() -> assertThat(sut.istVorhanden(benutzer1.getNachname(), benutzer1.getVorname())).isTrue(),
			() -> assertThat(sut.istVorhanden(benutzer1.getNachname(), Vorname.aus("Nicole").get())).isFalse(),
			() -> assertThat(sut.istVorhanden(benutzer3.getFirma())).isTrue(),
			() -> assertThat(sut.istVorhanden(Firma.aus("Lidl Stiftung & Co. KG").get())).isFalse());
	}

	@Test
	@Order(4)
	@Transactional
	@DisplayName("speichern")
	void test04()
	{
		var benutzer = Benutzer.aus(Nachname.aus("Harder").get(), Vorname.aus("Nicole").get()).get();
		sut.speichere(benutzer);
		assertThat(sut.finde(benutzer.getId())).contains(benutzer);
	}

	@Test
	@Order(5)
	@Transactional
	@DisplayName("löschen")
	void test05()
	{
		var benutzer = sut.finde(Nachname.aus("Harder").get(), Vorname.aus("Nicole").get()).get();
		sut.loesche(benutzer);
		assertThat(sut.finde(benutzer.getId())).isEmpty();
	}
}
