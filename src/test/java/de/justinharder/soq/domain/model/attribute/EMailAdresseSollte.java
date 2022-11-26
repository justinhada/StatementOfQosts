package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("EMailAdresse sollte")
class EMailAdresseSollte extends Testdaten
{
	private EMailAdresse sut;

	private Validation<Meldung, EMailAdresse> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = EMailAdresse.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.E_MAIL_ADRESSE_LEER));

		validierung = EMailAdresse.aus(LEER);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.E_MAIL_ADRESSE_LEER));

		validierung = EMailAdresse.aus(LEER_KURZ);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.E_MAIL_ADRESSE_LEER));

		validierung = EMailAdresse.aus(LEER_LANG);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.E_MAIL_ADRESSE_LEER));

		validierung = EMailAdresse.aus("harder");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.E_MAIL_ADRESSE_UNGUELTIG));

		validierung = EMailAdresse.aus("harder.de");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.E_MAIL_ADRESSE_UNGUELTIG));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = EMailAdresse.aus(E_MAIL_ADRESSE_1_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(E_MAIL_ADRESSE_1_WERT));

		validierung = EMailAdresse.aus(E_MAIL_ADRESSE_2_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(E_MAIL_ADRESSE_2_WERT));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(E_MAIL_ADRESSE_1).hasToString(E_MAIL_ADRESSE_1_WERT);
	}
}
