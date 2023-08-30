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
@DisplayName("BankenView sollte")
class BankenViewSollte extends ViewSollte
{
	@Test
	@DisplayName("Formular zur Erstellung aufrufen")
	void test01()
	{
		given()
			.header(HttpHeaders.ACCEPT, MediaType.TEXT_HTML)
			.when()
			.get(BANKEN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(
				containsString("46c317ae-25dd-4805-98ca-273e45d32815"),
				containsString("Oldenburgische Landesbank AG"),
				containsString("OLBO DE H2 XXX"),
				containsString("aaa8a25c-7589-4434-b668-4a78ab000628"),
				containsString("VR BANK Dinklage-Steinfeld eG"),
				containsString("GENO DE F1 DIK"));
	}

	@Test
	@DisplayName("Meldungen anzeigen, wenn Eingabedaten zur Erstellung leer sind")
	void test02()
	{
		given()
			.formParam(BEZEICHNUNG, LEER)
			.formParam(BIC, LEER)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(BANKEN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(containsString(Meldung.BEZEICHNUNG_LEER.text()), containsString(Meldung.BIC_LEER.text()));
	}

	@Test
	@DisplayName("Meldungen anzeigen, wenn Eingabedaten zur Erstellung bereits existieren")
	void test03()
	{
		given()
			.formParam(BEZEICHNUNG, "Oldenburgische Landesbank AG")
			.formParam(BIC, "OLBO DE H2 XXX")
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(BANKEN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(
				containsString(Meldung.BEZEICHNUNG_EXISTIERT_BEREITS.text()),
				containsString(Meldung.BIC_EXISTIERT_BEREITS.text()));
	}

	@Test
	@DisplayName("Meldung anzeigen, wenn Bank erstellt wurde")
	void test04()
	{
		var bezeichnung = "Volksbank Vechta";
		var bic = "GENO DE F1 VEC";

		given()
			.formParam(BEZEICHNUNG, bezeichnung)
			.formParam(BIC, bic)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(BANKEN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(
				containsString(Meldung.BANK_ERSTELLT.text()),
				containsString(bezeichnung),
				containsString(bic));
	}

	@Test
	@DisplayName("Formular zur Aktualisierung aufrufen")
	void test05()
	{
		given()
			.when()
			.get(BANKEN + "/" + bank1.getId().getWert())
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(
				containsString("46c317ae-25dd-4805-98ca-273e45d32815"),
				containsString("Oldenburgische Landesbank AG"),
				containsString("OLBO DE H2 XXX"));
	}
}
