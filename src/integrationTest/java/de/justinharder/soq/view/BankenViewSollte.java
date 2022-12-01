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
@DisplayName("BankRessource sollte")
class BankenRessourceSollte extends RessourceSollte
{
	@Test
	@DisplayName("Formular aufrufen")
	void test01()
	{
		given()
			.when()
			.get("/banken")
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(containsString("""
				<tr>
					<td>46c317ae-25dd-4805-98ca-273e45d32815</td>
					<td>Oldenburgische Landesbank AG</td>
					<td>OLBODEH2XXX</td>
				</tr>
				"""));
	}
}
