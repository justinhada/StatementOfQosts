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

@DisplayName("Kontoinhaber sollte")
class KontoinhaberSollte extends Testdaten
{
	private Kontoinhaber sut;

	private Validation<Meldungen, Kontoinhaber> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Kontoinhaber.aus(null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BENUTZER_LEER,
				Meldung.BANKVERBINDUNG_LEER));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Kontoinhaber.aus(BENUTZER_1, BANKVERBINDUNG_1);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBenutzer()).isEqualTo(BENUTZER_1),
			() -> assertThat(sut.getBankverbindung()).isEqualTo(BANKVERBINDUNG_1));

		validierung = Kontoinhaber.aus(BENUTZER_2, BANKVERBINDUNG_2);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBenutzer()).isEqualTo(BENUTZER_2),
			() -> assertThat(sut.getBankverbindung()).isEqualTo(BANKVERBINDUNG_2));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(KONTOINHABER_1).hasToString(
			"Kontoinhaber{ID=" + KONTOINHABER_1.getId() + ", Benutzer=Benutzer{ID=" + BENUTZER_1.getId() + ", Nachname=Harder, Vorname=Justin, Art=Privatperson}, Bankverbindung=Bankverbindung{ID=" + BANKVERBINDUNG_1.getId() + ", IBAN=DE87280200504008357800, Bank=Bank{ID=" + BANK_1.getId() + ", Bezeichnung=Oldenburgische Landesbank AG, BIC=OLBODEH2XXX}}}");
	}
}
