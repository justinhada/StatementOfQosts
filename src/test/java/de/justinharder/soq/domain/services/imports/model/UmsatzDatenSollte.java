package de.justinharder.soq.domain.services.imports.model;

import de.justinharder.ImportTestdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("UmsatzDaten sollte")
class UmsatzDatenSollte extends ImportTestdaten
{
	private UmsatzDaten sut;

	private Validation<Meldungen, UmsatzDaten> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = UmsatzDaten.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.IMPORT));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = UmsatzDaten.aus(IMPORT_1);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut).containsExactlyInAnyOrder(UMSATZ_DATUM_AUS_OLB));

		validierung = UmsatzDaten.aus(IMPORT_2);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut).containsExactlyInAnyOrder(UMSATZ_DATUM_AUS_VRB));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(UMSATZ_DATEN_1).hasToString(
			"[UmsatzDatum{AuftraggeberIBAN=DE87280200504008357800, Datum=31.10.2022, ZahlungsbeteiligterName=Laura Tiemerding, ZahlungsbeteiligterIBAN=DE28280651080012888000, ZahlungsbeteiligterBIC=GENODEF1DIK, Verwendungszweck=Wohnungsmiete, Betrag=447,48}]");
	}
}
