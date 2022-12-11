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
@DisplayName("BenutzerView sollte")
class BenutzerViewSollte extends ViewSollte
{
	@Test
	@DisplayName("Formular aufrufen")
	void test01()
	{
		given()
			.when()
			.get(BENUTZER)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML);
	}

	@Test
	@DisplayName("Meldungen anzeigen, wenn Eingabedaten leer sind")
	void test02()
	{
		given()
			.formParam(NACHNAME, LEER)
			.formParam(VORNAME, LEER)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(BENUTZER)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(containsString(Meldung.NACHNAME.text()), containsString(Meldung.VORNAME.text()));
	}

	@Test
	@DisplayName("Meldung anzeigen, wenn Benutzer erstellt wurde")
	void test04()
	{
		var nachname = "Harder";
		var vorname = "Justin";

		given()
			.formParam(NACHNAME, nachname)
			.formParam(VORNAME, vorname)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(BENUTZER)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(containsString(Meldung.BENUTZER_ERSTELLT.text()));
	}
}
