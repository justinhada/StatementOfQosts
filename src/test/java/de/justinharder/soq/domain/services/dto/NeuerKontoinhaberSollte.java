package de.justinharder.soq.domain.services.dto;

import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("NeuerKontoinhaber sollte")
class NeuerKontoinhaberSollte extends DTOSollte<NeuerKontoinhaber>
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

	@Test
	@DisplayName("zusammenfassen")
	void test02()
	{
		var sut = new NeuerKontoinhaber(BENUTZER_IDS, LEER)
			.fuegeMeldungHinzu(Meldung.BENUTZER_EXISTIERT_NICHT)
			.fasseZusammen(new NeuerKontoinhaber(BENUTZER_IDS, BANKVERBINDUNG_ID)
				.fuegeMeldungHinzu(Meldung.KONTOINHABER_ERSTELLT));

		assertAll(
			() -> assertThat(sut.getMeldungen(Schluessel.BENUTZER)).contains(Meldung.BENUTZER_EXISTIERT_NICHT),
			() -> assertThat(sut.getMeldungen(Schluessel.ALLGEMEIN)).contains(Meldung.KONTOINHABER_ERSTELLT));
	}
}
