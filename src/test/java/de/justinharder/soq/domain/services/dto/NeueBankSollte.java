package de.justinharder.soq.domain.services.dto;

import de.justinharder.soq.domain.model.meldung.Meldung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("NeueBank sollte")
class NeueBankSollte extends DTOSollte<NeueBank>
{
	@BeforeEach
	void setup()
	{
		super.setup(
			new NeueBank(BEZEICHNUNG_1_WERT, BIC_1_WERT),
			Meldung.BANK_ERSTELLT,
			Meldung.BEZEICHNUNG_EXISTIERT_BEREITS,
			Meldung.BIC_EXISTIERT_BEREITS);
	}

	@Test
	@DisplayName("NoArgsConstructor, Getter und Setter besitzen")
	void test01()
	{
		var sut = new NeueBank();
		sut.setBezeichnung(BEZEICHNUNG_1_WERT);
		sut.setBic(BIC_1_WERT);

		assertAll(
			() -> assertThat(sut.getBezeichnung()).isEqualTo(BEZEICHNUNG_1_WERT),
			() -> assertThat(sut.getBic()).isEqualTo(BIC_1_WERT),
			() -> assertThat(sut.myself()).isEqualTo(sut));
	}
}
