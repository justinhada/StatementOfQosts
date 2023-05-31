package de.justinharder.soq.domain.services.dto;

import de.justinharder.DTOTestdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("GespeicherteBankverbindung sollte")
class GespeicherteBankverbindungSollte extends DTOTestdaten
{
	private static final String ID = BANKVERBINDUNG_1.getId().getWert().toString();
	private static final String IBAN = BANKVERBINDUNG_1.getIban().toString();
	private static final String BANK = BANKVERBINDUNG_1.getBank().getBezeichnung().getWert();

	@Test
	@DisplayName("RequiredArgsConstructor und Getter besitzen")
	void test01()
	{
		var sut = new GespeicherteBankverbindung(ID, IBAN, BANK);

		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(ID),
			() -> assertThat(sut.getIban()).isEqualTo(IBAN),
			() -> assertThat(sut.getBank()).isEqualTo(BANK),
			() -> assertThat(sut.myself()).isEqualTo(sut));
	}
}
