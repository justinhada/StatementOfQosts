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

@DisplayName("Bankverbindung sollte")
class BankverbindungSollte extends Testdaten
{
	private Bankverbindung sut;

	private Validation<Meldungen, Bankverbindung> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Bankverbindung.aus(null, null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.IBAN, Meldung.BENUTZER_LEER,
				Meldung.BANK_LEER));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Bankverbindung.aus(IBAN_1, BENUTZER_1, BANK_1);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getIban()).isEqualTo(IBAN_1),
			() -> assertThat(sut.getBenutzer()).isEqualTo(BENUTZER_1),
			() -> assertThat(sut.getBank()).isEqualTo(BANK_1));

		validierung = Bankverbindung.aus(IBAN_2, BENUTZER_2, BANK_2);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getIban()).isEqualTo(IBAN_2),
			() -> assertThat(sut.getBenutzer()).isEqualTo(BENUTZER_2),
			() -> assertThat(sut.getBank()).isEqualTo(BANK_2));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(BANKVERBINDUNG_1).hasToString(
			"Bankverbindung{ID=" + BANKVERBINDUNG_1.getId() + ", IBAN=DE87280200504008357800, Benutzer=Benutzer{ID=" + BENUTZER_1.getId() + ", Nachname=Harder, Vorname=Justin}, Bank=Bank{ID=" + BANK_1.getId() + ", Bezeichnung=Oldenburgische Landesbank AG, BIC=OLBODEH2XXX}}");
	}
}