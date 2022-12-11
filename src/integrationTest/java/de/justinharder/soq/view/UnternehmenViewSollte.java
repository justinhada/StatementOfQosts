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
@DisplayName("UnternehmenView sollte")
class UnternehmenViewSollte extends ViewSollte
{
	@Test
	@DisplayName("Formular aufrufen")
	void test01()
	{
		given()
			.when()
			.get(UNTERNEHMEN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(
				containsString("cd4fb688-8854-426f-a3e9-d5c380b4e984"),
				containsString("Rewe-Markt GmbH"),
				containsString("bb1d3c64-468d-4109-971c-24dcf08993fc"),
				containsString("ALTE OLDENBURGER Krankenversicherung AG"));
	}

	@Test
	@DisplayName("Meldungen anzeigen, wenn Eingabedaten leer sind")
	void test02()
	{
		given()
			.formParam(FIRMA, LEER)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(UNTERNEHMEN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(containsString(Meldung.FIRMA_LEER.text()));
	}

	@Test
	@DisplayName("Meldung anzeigen, wenn Unternehmen erstellt wurde")
	void test04()
	{
		var firma = "Amazon.com, Inc.";

		given()
			.formParam(FIRMA, firma)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(UNTERNEHMEN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(
				containsString(Meldung.UNTERNEHMEN_ERSTELLT.text()),
				containsString(firma));
	}
}
