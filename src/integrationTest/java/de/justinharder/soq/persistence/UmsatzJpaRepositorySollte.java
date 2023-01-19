package de.justinharder.soq.persistence;

import de.justinharder.soq.IntegrationTest;
import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.attribute.Betrag;
import de.justinharder.soq.domain.model.attribute.Datum;
import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.model.attribute.Verwendungszweck;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
@DisplayName("UmsatzJpaRepository sollte")
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
	void setup()
	{
		super.setup();
		datum = umsatz1.getDatum();
		betrag = umsatz1.getBetrag();
		verwendungszweck = umsatz1.getVerwendungszweck();
		auftraggeber = umsatz1.getBankverbindungAuftraggeber();
		zahlungsbeteiligter = umsatz1.getBankverbindungZahlungsbeteiligter();
	}

	@Test
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
	@DisplayName("alle finden")
	void test02()
	{
		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(umsatz1, umsatz2);
	}

	@Test
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
