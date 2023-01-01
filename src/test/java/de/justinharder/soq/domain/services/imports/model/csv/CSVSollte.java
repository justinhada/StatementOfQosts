package de.justinharder.soq.domain.services.imports.model.csv;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("CSV sollte")
class CSVSollte extends Testdaten
{
	private static final CSV CSV_1 = CSV.aus(DATEI_1).get();

	private CSV sut;

	private Validation<Meldungen, CSV> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = CSV.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.DATEI));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = CSV.aus(DATEI_1);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getZeilen()).isEqualTo(Zeilen.aus(DATEI_1)));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(CSV_1).hasToString(
			"CSV{Zeilen=[Zeile{Spalten=[Inhaberkonto, Buchungsdatum, Valuta, Empfaenger/Auftraggeber, IBAN, BIC, Verwendungszweck, Betrag, Waehrung, Kundenreferenz, Bankreferenz, Primatnota, Transaktions-Code, Transaktions-Text]}, Zeile{Spalten=[DE87280200504008357800, 31.10.2022, 31.10.2022, LAURA TIEMERDING, DE28280651080012888000, GENODEF1DIK, WOHNUNGSMIETE , 447,48, EUR, NONREF, , 0004770, 152, DA-GUTSCHR]}]}");
	}
}
