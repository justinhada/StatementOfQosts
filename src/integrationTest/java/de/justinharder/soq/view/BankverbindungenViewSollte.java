package de.justinharder.soq.view;

import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
@DisplayName("BankverbindungenView sollte")
class BankverbindungenViewSollte extends ViewSollte
{
	@Test
	@DisplayName("Formular zur Erstellung aufrufen")
	void test01()
	{
		given()
			.when()
			.get(BANKVERBINDUNGEN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(
				containsString("46c317ae-25dd-4805-98ca-273e45d32815"),
				containsString("Oldenburgische Landesbank AG"));
	}

	@Test
	@DisplayName("Meldungen anzeigen, wenn Eingabedaten zur Erstellung leer sind")
	void test02()
	{
		given()
			.formParam(IBAN, LEER)
			.formParam(BANK_ID, LEER)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(BANKVERBINDUNGEN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(
				containsString(Meldung.IBAN_LEER.text()),
				containsString(Meldung.idLeer(Schluessel.BANK).text()));
	}

	@Test
	@DisplayName("Meldungen anzeigen, wenn zur Erstellung IBAN bereits existiert und BankID nicht existiert")
	void test03()
	{
		given()
			.formParam(IBAN, "DE87280200504008357800")
			.formParam(BANK_ID, "e8b9020a-8c41-4d6c-9afb-f3dd70953d70")
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(BANKVERBINDUNGEN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(
				containsString(Meldung.IBAN_EXISTIERT_BEREITS.text()),
				containsString(Meldung.BANK_EXISTIERT_NICHT.text()));
	}

	@Test
	@DisplayName("weiterleiten auf KontoinhaberView, wenn Bankverbindung erstellt wurde")
	void test04()
	{
		var iban = "DE02120300000000202051";
		var bankId = "46c317ae-25dd-4805-98ca-273e45d32815";

		given()
			.formParam(IBAN, iban)
			.formParam(BANK_ID, bankId)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
			.when()
			.post(BANKVERBINDUNGEN)
			.then()
			.statusCode(Response.Status.OK.getStatusCode())
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML)
			.body(
				containsString(iban),
				containsString("Oldenburgische Landesbank AG"),
				containsString("Justin Harder"),
				containsString("Laura Tiemerding"));
	}
}
