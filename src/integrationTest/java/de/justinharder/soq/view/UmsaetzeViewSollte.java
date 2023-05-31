package de.justinharder.soq.view;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
@DisplayName("UmsaetzeView sollte")
class UmsaetzeViewSollte extends ViewSollte
{
	@Test
	@DisplayName("Tabelle aufrufen")
	void test01()
	{
		given()
			.when()
			.get(UMSAETZE)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(
				containsString("188fae37-0294-4db9-b7e6-7f40c7f390f1"),
				containsString("01.01.2020"),
				containsString("1,00"),
				containsString("Wohnungsmiete"),
				containsString("DE87 2802 0050 4008 3578 00"),
				containsString("DE28 2806 5108 0012 8880 00"),
				containsString("1da15420-5b77-4d06-9fad-e62c0b62bb6f"),
				containsString("01.01.2021"),
				containsString("-1,00"),
				containsString("Lohn/Gehalt"),
				containsString("DE28 2806 5108 0012 8880 00"),
				containsString("DE87 2802 0050 4008 3578 00"));
	}
}
