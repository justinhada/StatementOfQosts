package de.justinharder.soq.domain.model;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Login sollte")
class LoginSollte extends Testdaten
{
	private Login sut;

	private Validation<Meldungen, Login> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Login.aus(null, null, null, null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.E_MAIL_ADRESSE_LEER,
				Meldung.BENUTZERNAME_LEER, Meldung.SALT, Meldung.PASSWORT_LEER, Meldung.BENUTZER_LEER));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Login.aus(E_JUSTIN, B_HARDER, SALT, P_JUSTIN, JUSTIN_HARDER);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getEMailAdresse()).isEqualTo(E_JUSTIN),
			() -> assertThat(sut.getBenutzername()).isEqualTo(B_HARDER),
			() -> assertThat(sut.getSalt()).isEqualTo(SALT),
			() -> assertThat(sut.getPasswort()).isEqualTo(P_JUSTIN),
			() -> assertThat(sut.getBenutzer()).isEqualTo(JUSTIN_HARDER));

		validierung = Login.aus(E_LAURA, B_TIEMERDING, SALT, P_LAURA, LAURA_TIEMERDING);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getEMailAdresse()).isEqualTo(E_LAURA),
			() -> assertThat(sut.getBenutzername()).isEqualTo(B_TIEMERDING),
			() -> assertThat(sut.getSalt()).isEqualTo(SALT),
			() -> assertThat(sut.getPasswort()).isEqualTo(P_LAURA),
			() -> assertThat(sut.getBenutzer()).isEqualTo(LAURA_TIEMERDING));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(Login.aus(E_JUSTIN, B_HARDER, SALT, P_JUSTIN, JUSTIN_HARDER).get().toString()).isNotEqualTo(
			Login.aus(E_LAURA, B_TIEMERDING, SALT, P_LAURA, LAURA_TIEMERDING).get().toString());
	}
}
