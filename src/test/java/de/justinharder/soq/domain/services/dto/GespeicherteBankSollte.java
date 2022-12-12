package de.justinharder.soq.domain.services.dto;

import de.justinharder.DtoTestdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("GespeicherteBank sollte")
class GespeicherteBankSollte extends DtoTestdaten
{
	private static final String ID = BANK_1.getId().getWert().toString();
	private static final String BEZEICHNUNG = BANK_1.getBezeichnung().getWert();
	private static final String BIC = BANK_1.getBic().getWert();

	@Test
	@DisplayName("RequiredArgsConstructor und Getter besitzen")
	void test01()
	{
		var sut = new GespeicherteBank(ID, BEZEICHNUNG, BIC);

		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(ID),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(BEZEICHNUNG),
			() -> assertThat(sut.getBic()).isEqualTo(BIC));
	}
}
