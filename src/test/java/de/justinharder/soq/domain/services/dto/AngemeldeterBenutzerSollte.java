package de.justinharder.soq.domain.services.dto;

import de.justinharder.DtoTestdaten;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("AngemeldeterBenutzer sollte")
class AngemeldeterBenutzerSollte extends DtoTestdaten
{
	@Test
	@DisplayName("NoArgsConstructor, Getter und Setter besitzen")
	void test01()
	{
		var sut = new AngemeldeterBenutzer();
		sut.setBenutzername(BENUTZERNAME_1_WERT);
		sut.setPasswort(PASSWORT_1_WERT);

		assertAll(
			() -> assertThat(sut.getBenutzername()).isEqualTo(BENUTZERNAME_1_WERT),
			() -> assertThat(sut.getPasswort()).isEqualTo(PASSWORT_1_WERT));
	}
}
