package de.justinharder.soq.domain.services.dto;

import de.justinharder.DtoTestdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("NeueBank sollte")
class NeueBankSollte extends DtoTestdaten
{
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
