package de.justinharder.soq.domain.services.dto;

import de.justinharder.soq.domain.model.meldung.Meldung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("NeuesUnternehmen sollte")
class NeuesUnternehmenSollte extends DTOSollte<NeuesUnternehmen>
{
	@BeforeEach
	void setup()
	{
		super.setup(
			new NeuesUnternehmen(FIRMA_1_WERT),
			Meldung.UNTERNEHMEN_ERSTELLT,
			Meldung.FIRMA_LEER,
			Meldung.FIRMA_EXISTIERT_BEREITS);
	}

	@Test
	@DisplayName("NoArgsConstructor, Getter und Setter besitzen")
	void test01()
	{
		var sut = new NeuesUnternehmen();
		sut.setFirma(FIRMA_1_WERT);

		assertAll(
			() -> assertThat(sut.getFirma()).isEqualTo(FIRMA_1_WERT),
			() -> assertThat(sut.myself()).isEqualTo(sut));
	}
}
