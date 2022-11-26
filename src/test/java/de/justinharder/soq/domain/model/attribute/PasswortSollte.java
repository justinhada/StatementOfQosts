package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Passwort sollte")
class PasswortSollte extends Testdaten
{
	private Passwort sut;

	private Validation<Meldungen, Passwort> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Passwort.aus(null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.PASSWORT_LEER, Meldung.SALT));

		validierung = Passwort.aus(SALT, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.PASSWORT_LEER));

		validierung = Passwort.aus(SALT, LEER_KURZ);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.PASSWORT_LEER));

		validierung = Passwort.aus(SALT, LEER_LANG);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.PASSWORT_LEER));

		validierung = Passwort.aus(SALT, "JustinHarder");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.PASSWORT_UNGUELTIG));

		validierung = Passwort.aus(SALT, "JustinHarder#");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.PASSWORT_UNGUELTIG));

		validierung = Passwort.aus(SALT, "JustinHarder98");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.PASSWORT_UNGUELTIG));

		validierung = Passwort.aus(SALT, "justinharder#98");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.PASSWORT_UNGUELTIG));

		validierung = Passwort.aus(SALT, "JUSTINHARDER#98");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.PASSWORT_UNGUELTIG));

		validierung = Passwort.aus(SALT, "#98");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.PASSWORT_UNGUELTIG));

		validierung = Passwort.aus(SALT, "98");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.PASSWORT_UNGUELTIG));

		validierung = Passwort.aus(SALT, "Justin#98");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.PASSWORT_UNGUELTIG));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Passwort.aus(SALT, "JustinHarder#98");
		sut = validierung.get();
		var andereValidierung = Passwort.aus(Salt.random(), "LauraTiemerding#01");
		var anderesPasswort = andereValidierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThrows(RuntimeException.class, andereValidierung::getError),
			() -> assertThat(sut).isNotEqualTo(anderesPasswort));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(PASSWORT_1.toString()).isNotEqualTo(PASSWORT_2.toString());
	}
}
