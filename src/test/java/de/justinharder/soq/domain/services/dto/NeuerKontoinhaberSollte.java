package de.justinharder.soq.domain.services.dto;

import de.justinharder.soq.domain.model.meldung.Meldung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("NeuerKontoinhaber sollte")
class NeuerKontoinhaberSollte extends DtoSollte<NeuerKontoinhaber>
{
	private static final List<String> BENUTZER_IDS = List.of(BENUTZER_1.getId().getWert().toString());
	private static final String BANKVERBINDUNG_ID = BANKVERBINDUNG_1.getId().getWert().toString();

	@BeforeEach
	void setup()
	{
		super.setup(
			new NeuerKontoinhaber(BENUTZER_IDS, BANKVERBINDUNG_ID),
			Meldung.KONTOINHABER_ERSTELLT,
			Meldung.BANKVERBINDUNG_EXISTIERT_NICHT,
			Meldung.BENUTZER_MINDESTAUSWAHL);
	}

	@Test
	@DisplayName("NoArgsConstructor, Getter und Setter besitzen")
	void test01()
	{
		var sut = new NeuerKontoinhaber();
		sut.setBenutzerIds(BENUTZER_IDS);
		sut.setBankverbindungId(BANKVERBINDUNG_ID);

		assertAll(
			() -> assertThat(sut.getBenutzerIds()).isEqualTo(BENUTZER_IDS),
			() -> assertThat(sut.getBankverbindungId()).isEqualTo(BANKVERBINDUNG_ID),
			() -> assertThat(sut.myself()).isEqualTo(sut));
	}
}
