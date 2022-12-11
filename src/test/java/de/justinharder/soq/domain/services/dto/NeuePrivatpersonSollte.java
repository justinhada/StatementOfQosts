package de.justinharder.soq.domain.services.dto;

import de.justinharder.soq.domain.model.meldung.Meldung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("NeuePrivatperson sollte")
class NeuePrivatpersonSollte extends DtoSollte<NeuePrivatperson>
{
	@BeforeEach
	void setup()
	{
		super.setup(
			new NeuePrivatperson(NACHNAME_1_WERT, VORNAME_1_WERT),
			Meldung.PRIVATPERSON_ERSTELLT,
			Meldung.NACHNAME_LEER,
			Meldung.VORNAME_LEER);
	}

	@Test
	@DisplayName("NoArgsConstructor, Getter und Setter besitzen")
	void test01()
	{
		var sut = new NeuePrivatperson();
		sut.setNachname(NACHNAME_1_WERT);
		sut.setVorname(VORNAME_1_WERT);

		assertAll(
			() -> assertThat(sut.getNachname()).isEqualTo(NACHNAME_1_WERT),
			() -> assertThat(sut.getVorname()).isEqualTo(VORNAME_1_WERT),
			() -> assertThat(sut.myself()).isEqualTo(sut));
	}
}
