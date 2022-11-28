package de.justinharder.soq.domain.services.imports.model;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.services.imports.Import;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("UmsatzDaten sollte")
class UmsatzDatenSollte extends Testdaten
{
	@Test
	@DisplayName("aus OLB-Import erstellt werden")
	void test01()
	{
		assertThat(Import.aus(OLB_UMSATZ_DATEN)).isEqualTo(UMSATZ_DATEN_AUS_OLB);
	}

	@Test
	@DisplayName("aus VRB-Import erstellt werden")
	void test02()
	{
		assertThat(Import.aus(VRB_UMSATZ_DATEN)).isEqualTo(UMSATZ_DATEN_AUS_VRB);
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(UMSATZ_DATEN_AUS_OLB).hasToString(
			"UmsatzDaten{AuftraggeberIBAN=DE87280200504008357800, Datum=31.10.2022, ZahlungsbeteiligterName=Laura Tiemerding, ZahlungsbeteiligterIBAN=DE28280651080012888000, ZahlungsbeteiligterBIC=GENODEF1DIK, Verwendungszweck=Wohnungsmiete, Betrag=447,48}");
	}
}
