package de.justinharder.soq.view;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;

@QuarkusTest
@DisplayName("BankRessource sollte")
class BankenRessourceSollte extends RessourceSollte
{
	@Test
	@DisplayName("Formular erfassen")
	void test01()
	{
		given()
			.when()
			.get("/banken")
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML);
	}
}
