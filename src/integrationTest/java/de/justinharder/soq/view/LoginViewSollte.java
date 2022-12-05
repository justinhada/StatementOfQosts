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
@DisplayName("LoginView sollte")
class LoginViewSollte extends ViewSollte
{
	@Test
	@DisplayName("Formular aufrufen")
	void test01()
	{
		given()
			.when()
			.get("/login")
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML);
	}

	@Test
	@DisplayName("Meldungen anzeigen, wenn Eingabedaten leer sind")
	void test02()
	{
		given()
			.formParam(BENUTZERNAME, LEER)
			.formParam(PASSWORT, LEER)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(LOGIN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(containsString(Meldung.BENUTZERNAME_LEER.text()), containsString(Meldung.PASSWORT_LEER.text()));
	}

	@Test
	@DisplayName("Meldung anzeigen, wenn Login erfolgreich war")
	void test04()
	{
		var benutzername = "hard3r";
		var passwort = "JustinHarder#98";

		given()
			.formParam(BENUTZERNAME, benutzername)
			.formParam(PASSWORT, passwort)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(LOGIN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(containsString(Meldung.LOGIN_ERFOLGREICH.text()));
	}
}
