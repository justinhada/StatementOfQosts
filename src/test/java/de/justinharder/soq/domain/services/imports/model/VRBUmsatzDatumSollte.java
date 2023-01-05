package de.justinharder.soq.domain.services.imports.model;

import de.justinharder.ImportTestdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("VRBUmsatzDatum sollte")
class VRBUmsatzDatumSollte extends ImportTestdaten
{
	@Test
	@DisplayName("sich drucken")
	void test01()
	{
		assertThat(VRB_UMSATZ_DATUM).hasToString(
			"VRBUmsatzDatum{BezeichnungAuftragskonto=VR Start, IBANAuftragskonto=DE28280651080012888000, BICAuftragskonto=GENODEF1DIK, BanknameAuftragskonto=VR BANK Dinklage-Steinfeld eG, Buchungstag=31.10.2022, Valutadatum=31.10.2022, NameZahlungsbeteiligter=Justin Harder, IBANZahlungsbeteiligter=DE87280200504008357800, BICZahlungsbeteiligter=OLBODEH2XXX, Buchungstext=Dauerauftragsbelast, Verwendungszweck=Wohnungsmiete, Betrag=-447,48, Waehrung=EUR, SaldoNachBuchung=10000,00, Bemerkung=, Kategorie=Sonstiges, Steuerrelevant=, GlaeubigerId=, Mandatsreferenz=}");
	}
}
