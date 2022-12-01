package de.justinharder.soq.domain.services.imports.model;

import de.justinharder.Testdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("VRBUmsatzDaten sollte")
class VRBUmsatzDatenSollte extends Testdaten
{
	@Test
	@DisplayName("sich drucken")
	void test01()
	{
		assertThat(VRB_UMSATZ_DATEN).hasToString(
			"VRBUmsatzDaten{BezeichnungAuftragskonto=VR Start, IBANAuftragskonto=DE28280651080012888000, BICAuftragskonto=GENODEF1DIK, BanknameAuftragskonto=VR BANK Dinklage-Steinfeld eG, Buchungstag=31.10.2022, Valutadatum=31.10.2022, NameZahlungsbeteiligter=Justin Harder, IBANZahlungsbeteiligter=DE87280200504008357800, BICZahlungsbeteiligter=OLBODEH2XXX, Buchungstext=Dauerauftragsbelast, Verwendungszweck=Wohnungsmiete /*DA-3* IBAN: DE87280200504008357800 BIC: OLBODEH2XXX, Betrag=-447,48, Waehrung=EUR, SaldoNachBuchung=10.000,00, Bemerkung=, Kategorie=Sonstiges, Steuerrelevant=, GlaeubigerId=, Mandatsreferenz=}");
	}
}