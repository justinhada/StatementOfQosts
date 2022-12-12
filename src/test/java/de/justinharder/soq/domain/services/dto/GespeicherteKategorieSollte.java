package de.justinharder.soq.domain.services.dto;

import de.justinharder.DtoTestdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("GespeicherteKategorie sollte")
public class GespeicherteKategorieSollte extends DtoTestdaten
{
	private static final String ID = KATEGORIE_1.getId().getWert().toString();
	private static final String BEZEICHNUNG = KATEGORIE_1.getBezeichnung().getWert();

	@Test
	@DisplayName("RequiredArgsConstructor und Getter besitzen")
	void test01()
	{
		var sut = new GespeicherteKategorie(ID, BEZEICHNUNG);

		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(ID),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(BEZEICHNUNG));
	}
}
