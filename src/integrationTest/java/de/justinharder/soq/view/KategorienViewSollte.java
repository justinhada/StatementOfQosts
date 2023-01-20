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
@DisplayName("KategorienView sollte")
class KategorienViewSollte extends ViewSollte
{
	@Test
	@DisplayName("Formular zur Erstellung aufrufen")
	void test01()
	{
		given()
			.when()
			.get(KATEGORIEN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(
				containsString("c69a09ed-e5ff-4ac6-90dd-d319ff43b9d6"),
				containsString("Lebensmittel"),
				containsString("8a977e9d-68e5-415e-948d-09b63b1e2907"),
				containsString("Supplements"));
	}

	@Test
	@DisplayName("Meldungen anzeigen, wenn Eingabedaten zur Erstellung leer sind")
	void test02()
	{
		given()
			.formParam(BEZEICHNUNG, LEER)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(KATEGORIEN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(containsString(Meldung.BEZEICHNUNG_LEER.text()));
	}

	@Test
	@DisplayName("Meldungen anzeigen, wenn Eingabedaten zur Erstellung bereits existieren")
	void test03()
	{
		given()
			.formParam(BEZEICHNUNG, "Lebensmittel")
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(KATEGORIEN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(containsString(Meldung.BEZEICHNUNG_EXISTIERT_BEREITS.text()));
	}

	@Test
	@DisplayName("Meldung anzeigen, wenn Kategorie erstellt wurde")
	void test04()
	{
		var bezeichnung = "Tanken";

		given()
			.formParam(BEZEICHNUNG, bezeichnung)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(KATEGORIEN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(containsString(Meldung.KATEGORIE_ERSTELLT.text()), containsString(bezeichnung));
	}
}
