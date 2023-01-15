package de.justinharder.soq.domain.model;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Umsatz sollte")
class UmsatzSollte extends Testdaten
{
	private Umsatz sut;

	private Validation<Meldungen, Umsatz> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Umsatz.aus(null, null, null, null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.DATUM, Meldung.BETRAG,
				Meldung.VERWENDUNGSZWECK, Meldung.BANKVERBINDUNG_LEER, Meldung.BANKVERBINDUNG_LEER));

		validierung = Umsatz.aus(DATUM_1, BETRAG_1, VERWENDUNGSZWECK_1, BANKVERBINDUNG_1, BANKVERBINDUNG_1);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BANKVERBINDUNGEN_GLEICH));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Umsatz.aus(DATUM_1, BETRAG_1, VERWENDUNGSZWECK_1, BANKVERBINDUNG_1, BANKVERBINDUNG_2);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getDatum()).isEqualTo(DATUM_1),
			() -> assertThat(sut.getBetrag()).isEqualTo(BETRAG_1),
			() -> assertThat(sut.getVerwendungszweck()).isEqualTo(VERWENDUNGSZWECK_1),
			() -> assertThat(sut.getBankverbindungAuftraggeber()).isEqualTo(BANKVERBINDUNG_1),
			() -> assertThat(sut.getBankverbindungZahlungsbeteiligter()).isEqualTo(BANKVERBINDUNG_2));

		validierung = Umsatz.aus(DATUM_2, BETRAG_2, VERWENDUNGSZWECK_2, BANKVERBINDUNG_2, BANKVERBINDUNG_1);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getDatum()).isEqualTo(DATUM_2),
			() -> assertThat(sut.getBetrag()).isEqualTo(BETRAG_2),
			() -> assertThat(sut.getVerwendungszweck()).isEqualTo(VERWENDUNGSZWECK_2),
			() -> assertThat(sut.getBankverbindungAuftraggeber()).isEqualTo(BANKVERBINDUNG_2),
			() -> assertThat(sut.getBankverbindungZahlungsbeteiligter()).isEqualTo(BANKVERBINDUNG_1));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(UMSATZ_1).hasToString(
			"Umsatz{ID=" + UMSATZ_1.getId() + ", Datum=01.01.2020, Betrag=1, Verwendungszweck=Wohnungsmiete, BankverbindungAuftraggeber=Bankverbindung{ID=" + BANKVERBINDUNG_1.getId() + ", IBAN=DE87280200504008357800, Bank=Bank{ID=" + BANK_1.getId() + ", Bezeichnung=Oldenburgische Landesbank AG, BIC=OLBODEH2XXX}}, BankverbindungZahlungsbeteiligter=Bankverbindung{ID=" + BANKVERBINDUNG_2.getId() + ", IBAN=DE28280651080012888000, Bank=Bank{ID=" + BANK_2.getId() + ", Bezeichnung=Oldenburgische Landesbank AG, BIC=OLBODEH2XXX}}}");
	}
}
