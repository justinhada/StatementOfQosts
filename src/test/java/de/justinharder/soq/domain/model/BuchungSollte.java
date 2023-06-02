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

@DisplayName("Buchung sollte")
class BuchungSollte extends Testdaten
{
	private Buchung sut;

	private Validation<Meldungen, Buchung> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Buchung.aus(null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.UMSATZ_LEER,
				Meldung.KATEGORIE_LEER));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Buchung.aus(UMSATZ_1, KATEGORIE_1);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getUmsatz()).isEqualTo(UMSATZ_1),
			() -> assertThat(sut.getKategorie()).isEqualTo(KATEGORIE_1));

		validierung = Buchung.aus(UMSATZ_2, KATEGORIE_2);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getUmsatz()).isEqualTo(UMSATZ_2),
			() -> assertThat(sut.getKategorie()).isEqualTo(KATEGORIE_2));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(BUCHUNG_1).hasToString(
			"Buchung{ID=" + BUCHUNG_1.getId() + ", Umsatz=Umsatz{ID=" + UMSATZ_1.getId() + ", Datum=01.01.2020, Betrag=1,00, Verwendungszweck=Wohnungsmiete, BankverbindungAuftraggeber=Bankverbindung{ID=" + BANKVERBINDUNG_1.getId() + ", IBAN=DE87 2802 0050 4008 3578 00, Bank=Bank{ID=" + BANK_1.getId() + ", Bezeichnung=Oldenburgische Landesbank AG, BIC=OLBO DE H2 XXX}}, BankverbindungZahlungsbeteiligter=Bankverbindung{ID=" + BANKVERBINDUNG_2.getId() + ", IBAN=DE28 2806 5108 0012 8880 00, Bank=Bank{ID=" + BANK_2.getId() + ", Bezeichnung=Volksbank Vechta eG, BIC=GENO DE F1 DIK}}}, Kategorie=Kategorie{ID=" + KATEGORIE_1.getId() + ", Bezeichnung=Lebensmittel}}");
	}
}
