package de.justinharder.soq.persistence;

import de.justinharder.soq.IntegrationTest;
import de.justinharder.soq.domain.model.Bank;
import de.justinharder.soq.domain.model.attribute.BIC;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
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
@DisplayName("BankJpaRepository sollte")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BankJpaRepositorySollte extends IntegrationTest
{
	@Inject
	BankJpaRepository sut;

	@Test
	@Order(1)
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.finde((ID) null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichere(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.loesche(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde((Bezeichnung) null)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde((BIC) null)),
			() -> assertThrows(NullPointerException.class, () -> sut.istVorhanden((Bezeichnung) null)),
			() -> assertThrows(NullPointerException.class, () -> sut.istVorhanden((BIC) null)));
	}

	@Test
	@Order(2)
	@DisplayName("alle finden")
	void test02()
	{
		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(bank1, bank2);
	}

	@Test
	@Order(3)
	@DisplayName("finden")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.finde(bank1.getId())).contains(bank1),
			() -> assertThat(sut.finde(bank2.getId())).contains(bank2),
			() -> assertThat(sut.finde(ID.aus("f22396bf-a21b-4b0b-b5c2-798b130a24c1", Schluessel.BANK).get()))
				.isEmpty(),
			() -> assertThat(sut.finde(bank1.getBezeichnung())).contains(bank1),
			() -> assertThat(sut.finde(Bezeichnung.aus("Landessparkasse zu Oldenburg").get())).isEmpty(),
			() -> assertThat(sut.finde(bank1.getBic())).contains(bank1),
			() -> assertThat(sut.finde(BIC.aus("SLZODE22XXX").get())).isEmpty(),
			() -> assertThat(sut.istVorhanden(bank1.getBezeichnung())).isTrue(),
			() -> assertThat(sut.istVorhanden(Bezeichnung.aus("Landessparkasse zu Oldenburg").get())).isFalse(),
			() -> assertThat(sut.istVorhanden(bank1.getBic())).isTrue(),
			() -> assertThat(sut.istVorhanden(BIC.aus("SLZODE22XXX").get())).isFalse());
	}

	@Test
	@Order(4)
	@Transactional
	@DisplayName("speichern")
	void test04()
	{
		var bank = Bank.aus(Bezeichnung.aus("Volksbank Vechta").get(), BIC.aus("GENODEF1VEC").get()).get();
		sut.speichere(bank);
		assertThat(sut.finde(bank.getId())).isNotEmpty();
	}

	@Test
	@Order(5)
	@Transactional
	@DisplayName("löschen")
	void test05()
	{
		var bank = sut.finde(Bezeichnung.aus("Volksbank Vechta").get()).get();
		sut.loesche(bank);
		assertThat(sut.finde(bank.getId())).isEmpty();
	}
}
