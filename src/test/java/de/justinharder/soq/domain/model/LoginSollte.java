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
		validierung = Login.aus(E_MAIL_ADRESSE_1, BENUTZERNAME_1, SALT, PASSWORT_1, BENUTZER_1);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getEMailAdresse()).isEqualTo(E_MAIL_ADRESSE_1),
			() -> assertThat(sut.getBenutzername()).isEqualTo(BENUTZERNAME_1),
			() -> assertThat(sut.getSalt()).isEqualTo(SALT),
			() -> assertThat(sut.getPasswort()).isEqualTo(PASSWORT_1),
			() -> assertThat(sut.getBenutzer()).isEqualTo(BENUTZER_1));

		validierung = Login.aus(E_MAIL_ADRESSE_2, BENUTZERNAME_2, SALT, PASSWORT_2, BENUTZER_2);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getEMailAdresse()).isEqualTo(E_MAIL_ADRESSE_2),
			() -> assertThat(sut.getBenutzername()).isEqualTo(BENUTZERNAME_2),
			() -> assertThat(sut.getSalt()).isEqualTo(SALT),
			() -> assertThat(sut.getPasswort()).isEqualTo(PASSWORT_2),
			() -> assertThat(sut.getBenutzer()).isEqualTo(BENUTZER_2));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(LOGIN_1).hasToString(
			"Login{ID=" + LOGIN_1.getId() + ", E-Mail-Adresse=justinharder@t-online.de, Benutzername=hard3r, Salt=" + SALT + ", Passwort=" + PASSWORT_1 + ", Benutzer=Benutzer{ID=" + BENUTZER_1.getId() + ", Nachname=Harder, Vorname=Justin}}");
	}
}
