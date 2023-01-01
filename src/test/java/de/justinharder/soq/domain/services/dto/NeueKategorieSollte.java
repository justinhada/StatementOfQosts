package de.justinharder.soq.domain.services.dto;

import de.justinharder.soq.domain.model.meldung.Meldung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NeueKategorieSollte extends DTOSollte<NeueKategorie>
{
	@BeforeEach
	void setup()
	{
		super.setup(
			new NeueKategorie(BEZEICHNUNG_3_WERT),
			Meldung.KATEGORIE_ERSTELLT,
			Meldung.BEZEICHNUNG_LEER,
			Meldung.BEZEICHNUNG_EXISTIERT_BEREITS);
	}

	@Test
	@DisplayName("NoArgsConstructor, Getter und Setter besitzen")
	void test01()
	{
		var sut = new NeueKategorie();
		sut.setBezeichnung(BEZEICHNUNG_1_WERT);

		assertAll(
			() -> assertThat(sut.getBezeichnung()).isEqualTo(BEZEICHNUNG_1_WERT),
			() -> assertThat(sut.myself()).isEqualTo(sut));
	}
}
