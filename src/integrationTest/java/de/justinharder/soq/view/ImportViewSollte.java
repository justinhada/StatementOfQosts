package de.justinharder.soq.view;

import de.justinharder.soq.domain.model.meldung.Meldung;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
@DisplayName("ImportView sollte")
class ImportViewSollte extends ViewSollte
{
	@Test
	@DisplayName("Formular zum Import aufrufen")
	void test01()
	{
		given()
			.when()
			.get(IMPORT)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML);
	}

	@Test
	@DisplayName("Meldungen anzeigen, wenn Eingabedaten zum Import leer/ungültig sind")
	void test02()
	{
		given()
			.formParam(HERAUSGEBER, "0")
			.multiPart(DATEI, "Dateiname", LEER.getBytes(StandardCharsets.UTF_8))
			.header(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA)
			.when()
			.post(IMPORT)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(containsString(Meldung.HERAUSGEBER_UNGUELTIG.text()), containsString(Meldung.DATEI.text()));
	}
}
