package de.justinharder.soq.domain.services.dto;

import de.justinharder.DTOTestdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("GespeicherterKontoinhaber sollte")
class GespeicherterKontoinhaberSollte extends DTOTestdaten
{
	private static final String ID = KONTOINHABER_1.getId().getWert().toString();
	private static final String KONTOINHABER = KONTOINHABER_1.getBenutzer().getNachname().getWert() + " "
		+ KONTOINHABER_1.getBenutzer().getVorname().getWert();
	private static final String IBAN = KONTOINHABER_1.getBankverbindung().getIban().toString();
	private static final String BANK = KONTOINHABER_1.getBankverbindung().getBank().getBezeichnung().getWert();

	@Test
	@DisplayName("RequiredArgsConstructor und Getter besitzen")
	void test01()
	{
		var sut = new GespeicherterKontoinhaber(ID, KONTOINHABER, IBAN, BANK);

		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(ID),
			() -> assertThat(sut.getKontoinhaber()).isEqualTo(KONTOINHABER),
			() -> assertThat(sut.getIban()).isEqualTo(IBAN),
			() -> assertThat(sut.getBank()).isEqualTo(BANK));
	}
}
