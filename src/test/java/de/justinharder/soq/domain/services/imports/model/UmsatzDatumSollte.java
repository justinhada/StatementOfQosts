package de.justinharder.soq.domain.services.imports.model;

import de.justinharder.Testdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("UmsatzDaten sollte")
class UmsatzDatumSollte extends Testdaten
{
	@Test
	@DisplayName("aus OLB-Import erstellt werden")
	void test01()
	{
		assertThat(UmsatzDatum.aus(OLB_UMSATZ_DATUM)).isEqualTo(UMSATZ_DATUM_AUS_OLB);
	}

	@Test
	@DisplayName("aus VRB-Import erstellt werden")
	void test02()
	{
		assertThat(UmsatzDatum.aus(VRB_UMSATZ_DATUM)).isEqualTo(UMSATZ_DATUM_AUS_VRB);
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(UMSATZ_DATUM_AUS_OLB).hasToString(
			"UmsatzDaten{AuftraggeberIBAN=DE87280200504008357800, Datum=31.10.2022, ZahlungsbeteiligterName=Laura Tiemerding, ZahlungsbeteiligterIBAN=DE28280651080012888000, ZahlungsbeteiligterBIC=GENODEF1DIK, Verwendungszweck=Wohnungsmiete, Betrag=447,48}");
	}
}
