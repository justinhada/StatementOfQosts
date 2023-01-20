package de.justinharder.soq.persistence;

import de.justinharder.soq.IntegrationTest;
import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.Umsatz;
import de.justinharder.soq.domain.model.attribute.Betrag;
import de.justinharder.soq.domain.model.attribute.Datum;
import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.model.attribute.Verwendungszweck;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
@DisplayName("UmsatzJpaRepository sollte")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UmsatzJpaRepositorySollte extends IntegrationTest
{
	@Inject
	UmsatzJpaRepository sut;

	private Datum datum;
	private Betrag betrag;
	private Verwendungszweck verwendungszweck;
	private Bankverbindung auftraggeber;
	private Bankverbindung zahlungsbeteiligter;

	@BeforeEach
	public void setup()
	{
		super.setup();
		datum = umsatz1.getDatum();
		betrag = umsatz1.getBetrag();
		verwendungszweck = umsatz1.getVerwendungszweck();
		auftraggeber = umsatz1.getBankverbindungAuftraggeber();
		zahlungsbeteiligter = umsatz1.getBankverbindungZahlungsbeteiligter();
	}

	@Test
	@Order(1)
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichere(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.loesche(null)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.finde(null, betrag, verwendungszweck, auftraggeber, zahlungsbeteiligter)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.finde(datum, null, verwendungszweck, auftraggeber, zahlungsbeteiligter)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.finde(datum, betrag, null, auftraggeber, zahlungsbeteiligter)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.finde(datum, betrag, verwendungszweck, null, zahlungsbeteiligter)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.finde(datum, betrag, verwendungszweck, auftraggeber, null)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.istVorhanden(null, betrag, verwendungszweck, auftraggeber, zahlungsbeteiligter)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.istVorhanden(datum, null, verwendungszweck, auftraggeber, zahlungsbeteiligter)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.istVorhanden(datum, betrag, null, auftraggeber, zahlungsbeteiligter)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.istVorhanden(datum, betrag, verwendungszweck, null, zahlungsbeteiligter)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.istVorhanden(datum, betrag, verwendungszweck, auftraggeber, null)));
	}

	@Test
	@Order(2)
	@DisplayName("alle finden")
	void test02()
	{
		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(umsatz1, umsatz2, umsatz3);
	}

	@Test
	@Order(3)
	@DisplayName("finden")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.finde(umsatz1.getId())).contains(umsatz1),
			() -> assertThat(sut.finde(umsatz2.getId())).contains(umsatz2),
			() -> assertThat(sut.finde(ID.aus("f22396bf-a21b-4b0b-b5c2-798b130a24c1", Schluessel.UMSATZ).get()))
				.isEmpty(),
			() -> assertThat(sut.finde(datum, betrag, verwendungszweck, auftraggeber, zahlungsbeteiligter))
				.contains(umsatz1),
			() -> assertThat(sut.finde(datum, betrag, verwendungszweck, auftraggeber, auftraggeber)).isEmpty(),
			() -> assertThat(sut.istVorhanden(datum, betrag, verwendungszweck, auftraggeber, zahlungsbeteiligter))
				.isTrue(),
			() -> assertThat(sut.istVorhanden(datum, betrag, verwendungszweck, auftraggeber, auftraggeber)).isFalse());
	}

	@Test
	@Order(4)
	@Transactional
	@DisplayName("speichern")
	void test04()
	{
		var umsatz = Umsatz.aus(datum, betrag, verwendungszweck, bankverbindung2, bankverbindung1).get();
		sut.speichere(umsatz);
		assertThat(sut.finde(umsatz.getId())).contains(umsatz);
	}

	@Test
	@Order(5)
	@Transactional
	@DisplayName("löschen")
	void test05()
	{
		var umsatz = sut.finde(datum, betrag, verwendungszweck, bankverbindung2, bankverbindung1).get();
		sut.loesche(umsatz);
		assertThat(sut.finde(umsatz.getId())).isEmpty();
	}
}
