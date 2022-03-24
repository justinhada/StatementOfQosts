package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("E-Mail-Adresse sollte")
class EmailAdresseSollte extends Testdaten
{
	private EmailAdresse sut;

	private Validation<Meldung, EmailAdresse> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = EmailAdresse.aus(null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.E_MAIL_ADRESSE_LEER));

		validierung = EmailAdresse.aus("");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.E_MAIL_ADRESSE_LEER));

		validierung = EmailAdresse.aus(" ");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.E_MAIL_ADRESSE_LEER));

		validierung = EmailAdresse.aus("             ");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.E_MAIL_ADRESSE_LEER));

		validierung = EmailAdresse.aus("harder");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.E_MAIL_ADRESSE_UNGUELTIG));

		validierung = EmailAdresse.aus("harder.de");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).isEqualTo(Meldung.E_MAIL_ADRESSE_UNGUELTIG));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = EmailAdresse.aus(E_JUSTIN_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(E_JUSTIN_WERT));

		validierung = EmailAdresse.aus(E_LAURA_WERT);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getWert()).isEqualTo(E_LAURA_WERT));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(EmailAdresse.aus(E_JUSTIN_WERT).get()).hasToString(E_JUSTIN_WERT);
	}
}
