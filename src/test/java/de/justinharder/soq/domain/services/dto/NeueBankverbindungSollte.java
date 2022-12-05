package de.justinharder.soq.domain.services.dto;

import de.justinharder.soq.domain.model.meldung.Meldung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("NeueBankverbindung sollte")
class NeueBankverbindungSollte extends DtoSollte<NeueBankverbindung>
{
	@BeforeEach
	void setup()
	{
		super.setup(
			new NeueBankverbindung(
				IBAN_1_WERT,
				BENUTZER_1.getId().getWert().toString(),
				BANK_1.getId().getWert().toString()),
			Meldung.BANKVERBINDUNG_ERSTELLT,
			Meldung.IBAN_EXISTIERT_BEREITS,
			Meldung.BENUTZER_EXISTIERT_NICHT);
	}

	@Test
	@DisplayName("NoArgsConstructor, Getter und Setter besitzen")
	void test01()
	{
		var sut = new NeueBankverbindung();
		sut.setIban(IBAN_1_WERT);
		sut.setBenutzerId(BENUTZER_1.getId().getWert().toString());
		sut.setBankId(BANK_1.getId().getWert().toString());

		assertAll(
			() -> assertThat(sut.getIban()).isEqualTo(IBAN_1_WERT),
			() -> assertThat(sut.getBenutzerId()).isEqualTo(BENUTZER_1.getId().getWert().toString()),
			() -> assertThat(sut.getBankId()).isEqualTo(BANK_1.getId().getWert().toString()),
			() -> assertThat(sut.myself()).isEqualTo(sut));
	}
}
