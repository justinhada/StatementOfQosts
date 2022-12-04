package de.justinharder.soq.domain.services.dto;

import de.justinharder.DtoTestdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("NeueBankverbindung sollte")
class NeueBankverbindungSollte extends DtoTestdaten
{
	private static final String BENUTZER_ID = UUID.randomUUID().toString();
	private static final String BANK_ID = UUID.randomUUID().toString();

	@Test
	@DisplayName("NoArgsConstructor, Getter und Setter besitzen")
	void test01()
	{
		var sut = new NeueBankverbindung();
		sut.setIban(IBAN_1_WERT);
		sut.setBenutzerId(BENUTZER_ID);
		sut.setBankId(BANK_ID);

		assertAll(
			() -> assertThat(sut.getIban()).isEqualTo(IBAN_1_WERT),
			() -> assertThat(sut.getBenutzerId()).isEqualTo(BENUTZER_ID),
			() -> assertThat(sut.getBankId()).isEqualTo(BANK_ID));
	}
}
