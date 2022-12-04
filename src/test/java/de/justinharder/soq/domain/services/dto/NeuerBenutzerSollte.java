package de.justinharder.soq.domain.services.dto;

import de.justinharder.DtoTestdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("NeuerBenutzer sollte")
class NeuerBenutzerSollte extends DtoTestdaten
{
	@Test
	@DisplayName("NoArgsConstructor, Getter und Setter besitzen")
	void test01()
	{
		var sut = new NeuerBenutzer();
		sut.setEmailadresse(E_MAIL_ADRESSE_1_WERT);
		sut.setBenutzername(BENUTZERNAME_1_WERT);
		sut.setNachname(NACHNAME_1_WERT);
		sut.setVorname(VORNAME_1_WERT);
		sut.setPasswort(PASSWORT_1_WERT);

		assertAll(
			() -> assertThat(sut.getEmailadresse()).isEqualTo(E_MAIL_ADRESSE_1_WERT),
			() -> assertThat(sut.getBenutzername()).isEqualTo(BENUTZERNAME_1_WERT),
			() -> assertThat(sut.getNachname()).isEqualTo(NACHNAME_1_WERT),
			() -> assertThat(sut.getVorname()).isEqualTo(VORNAME_1_WERT),
			() -> assertThat(sut.getPasswort()).isEqualTo(PASSWORT_1_WERT),
			() -> assertThat(sut.myself()).isEqualTo(sut));
	}
}
