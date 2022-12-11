package de.justinharder.soq.domain.services.dto;

import de.justinharder.DtoTestdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("GespeichertePrivatperson sollte")
class GespeichertePrivatpersonSollte extends DtoTestdaten
{
	private static final String ID = BENUTZER_1.getId().getWert().toString();
	private static final String NACHNAME = BENUTZER_1.getNachname().getWert();
	private static final String VORNAME = BENUTZER_1.getVorname().getWert();

	@Test
	@DisplayName("RequiredArgsConstructor und Getter besitzen")
	void test01()
	{
		var sut = new GespeichertePrivatperson(ID, NACHNAME, VORNAME);

		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(ID),
			() -> assertThat(sut.getNachname()).isEqualTo(NACHNAME),
			() -> assertThat(sut.getVorname()).isEqualTo(VORNAME));
	}
}
