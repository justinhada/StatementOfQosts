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
@DisplayName("KontoinhaberView sollte")
class KontoinhaberViewSollte extends ViewSollte
{
	@Test
	@DisplayName("Tabelle aufrufen")
	void test01()
	{
		given()
			.when()
			.get(KONTOINHABER)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(
				containsString("bc01e451-051b-4bfa-979f-2acff87ba5a2"),
				containsString("Harder"),
				containsString("Justin"),
				containsString("DE87280200504008357800"),
				containsString("Oldenburgische Landesbank AG"),
				containsString("7f94d5e5-3972-4f44-b81b-994a244ba3ad"),
				containsString("Tiemerding"),
				containsString("Laura"),
				containsString("DE28280651080012888000"),
				containsString("VR BANK Dinklage-Steinfeld eG"));
	}
}
