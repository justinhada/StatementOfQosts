package de.justinharder.soq.domain.model;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.attribute.Art;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("AusgabeEinnahme sollte")
class AusgabeEinnahmeSollte extends Testdaten
{
	private AusgabeEinnahme sut;

	private Validation<Meldungen, AusgabeEinnahme> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = AusgabeEinnahme.aus(null, null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.ART, Meldung.UMSATZ_LEER,
				Meldung.KATEGORIE_LEER));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = AusgabeEinnahme.aus(Art.AUSGABE, UMSATZ_1, KATEGORIE_1);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getArt()).isEqualTo(Art.AUSGABE),
			() -> assertThat(sut.getUmsatz()).isEqualTo(UMSATZ_1),
			() -> assertThat(sut.getKategorie()).isEqualTo(KATEGORIE_1));

		validierung = AusgabeEinnahme.aus(Art.EINNAHME, UMSATZ_2, KATEGORIE_2);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getArt()).isEqualTo(Art.EINNAHME),
			() -> assertThat(sut.getUmsatz()).isEqualTo(UMSATZ_2),
			() -> assertThat(sut.getKategorie()).isEqualTo(KATEGORIE_2));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(AUSGABE_EINNAHME_1).hasToString(
			"AusgabeEinnahme{ID=" + AUSGABE_EINNAHME_1.getId() + ", Art=AUSGABE, Umsatz=Umsatz{ID=" + UMSATZ_1.getId() + ", Datum=01.01.2020, Betrag=1, Verwendungszweck=Wohnungsmiete, BankverbindungSender=Bankverbindung{ID=" + BANKVERBINDUNG_1.getId() + ", IBAN=DE87280200504008357800, Benutzer=Benutzer{ID=" + BENUTZER_1.getId() + ", Nachname=Harder, Vorname=Justin}, Bank=Bank{ID=" + BANK_1.getId() + ", Bezeichnung=Oldenburgische Landesbank AG, BIC=OLBODEH2XXX}}, BankverbindungEmpfaenger=Bankverbindung{ID=" + BANKVERBINDUNG_2.getId() + ", IBAN=DE28280651080012888000, Benutzer=Benutzer{ID=" + BENUTZER_2.getId() + ", Nachname=Tiemerding, Vorname=Laura}, Bank=Bank{ID=" + BANK_2.getId() + ", Bezeichnung=Oldenburgische Landesbank AG, BIC=OLBODEH2XXX}}}, Kategorie=Kategorie{ID=" + KATEGORIE_1.getId() + ", Bezeichnung=Lebensmittel}}");
	}
}
