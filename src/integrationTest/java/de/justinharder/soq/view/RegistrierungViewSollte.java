package de.justinharder.soq.view;

import de.justinharder.soq.domain.model.meldung.Meldung;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
@DisplayName("RegistrierungView sollte")
class RegistrierungViewSollte extends ViewSollte
{
	@Test
	@DisplayName("Formular aufrufen")
	void test01()
	{
		given()
			.when()
			.get(REGISTRIERUNG)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML);
	}

	@Test
	@DisplayName("Meldungen anzeigen, wenn Eingabedaten leer sind")
	void test02()
	{
		given()
			.formParam(E_MAIL_ADRESSE, LEER)
			.formParam(BENUTZERNAME, LEER)
			.formParam(NACHNAME, LEER)
			.formParam(VORNAME, LEER)
			.formParam(PASSWORT, LEER)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(REGISTRIERUNG)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(
				containsString(Meldung.E_MAIL_ADRESSE_LEER.text()),
				containsString(Meldung.BENUTZERNAME_LEER.text()),
				containsString(Meldung.NACHNAME.text()),
				containsString(Meldung.VORNAME.text()),
				containsString(Meldung.PASSWORT_LEER.text()));
	}

	@Test
	@DisplayName("Meldung anzeigen, wenn Benutzer und Login erstellt wurden")
	void test04()
	{
		var eMailAdresse = "justinharder@gmail.com";
		var benutzername = "justinharder";
		var nachname = "Harder";
		var vorname = "Justin";
		var passwort = "Justinharder#1234";

		given()
			.formParam(E_MAIL_ADRESSE, eMailAdresse)
			.formParam(BENUTZERNAME, benutzername)
			.formParam(NACHNAME, nachname)
			.formParam(VORNAME, vorname)
			.formParam(PASSWORT, passwort)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(REGISTRIERUNG)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(containsString(Meldung.BENUTZER_ERSTELLT.text()));
	}
}
