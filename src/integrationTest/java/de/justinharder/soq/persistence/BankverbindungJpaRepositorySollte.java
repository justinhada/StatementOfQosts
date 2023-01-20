package de.justinharder.soq.persistence;

import de.justinharder.soq.IntegrationTest;
import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.attribute.IBAN;
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
@DisplayName("BankverbindungJpaRepository sollte")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BankverbindungJpaRepositorySollte extends IntegrationTest
{
	@Inject
	BankverbindungJpaRepository sut;

	@Test
	@Order(1)
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.finde((ID) null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichere(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.loesche(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde((IBAN) null)),
			() -> assertThrows(NullPointerException.class, () -> sut.istVorhanden(null)));
	}

	@Test
	@Order(2)
	@DisplayName("alle finden")
	void test02()
	{
		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(bankverbindung1, bankverbindung2);
	}

	@Test
	@Order(3)
	@DisplayName("finden")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.finde(bankverbindung1.getId())).contains(bankverbindung1),
			() -> assertThat(sut.finde(bankverbindung2.getId())).contains(bankverbindung2),
			() -> assertThat(sut.finde(ID.aus("f22396bf-a21b-4b0b-b5c2-798b130a24c1", Schluessel.BANKVERBINDUNG)
				.get())).isEmpty(),
			() -> assertThat(sut.finde(bankverbindung1.getIban())).contains(bankverbindung1),
			() -> assertThat(sut.finde(IBAN.aus("DE68500105178152985159").get())).isEmpty(),
			() -> assertThat(sut.istVorhanden(bankverbindung1.getIban())).isTrue(),
			() -> assertThat(sut.istVorhanden(IBAN.aus("DE68500105178152985159").get())).isFalse());
	}

	@Test
	@Order(4)
	@Transactional
	@DisplayName("speichern")
	void test04()
	{
		var bankverbindung = Bankverbindung.aus(IBAN.aus("DE68500105178152985159").get(), bank1).get();
		sut.speichere(bankverbindung);
		assertThat(sut.finde(bankverbindung.getId())).contains(bankverbindung);
	}

	@Test
	@Order(5)
	@Transactional
	@DisplayName("löschen")
	void test05()
	{
		var bankverbindung = sut.finde(IBAN.aus("DE68500105178152985159").get()).get();
		sut.loesche(bankverbindung);
		assertThat(sut.finde(bankverbindung.getId())).isEmpty();
	}
}
