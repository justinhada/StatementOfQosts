package de.justinharder.soq.domain.services.dto;

import de.justinharder.DtoTestdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("GespeichertesUnternehmen sollte")
class GespeichertesUnternehmenSollte extends DtoTestdaten
{
	private static final String ID = BENUTZER_3.getId().getWert().toString();
	private static final String FIRMA = BENUTZER_3.getFirma().getWert();

	@Test
	@DisplayName("RequiredArgsConstructor und Getter besitzen")
	void test01()
	{
		var sut = new GespeichertesUnternehmen(ID, FIRMA);

		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(ID),
			() -> assertThat(sut.getFirma()).isEqualTo(FIRMA));
	}
}
