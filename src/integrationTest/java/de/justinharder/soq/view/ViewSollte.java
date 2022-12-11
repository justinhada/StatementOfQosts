package de.justinharder.soq.view;

import io.restassured.RestAssured;
import io.restassured.config.DecoderConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.jupiter.api.BeforeAll;

public class ViewSollte
{
	protected static final String LEER = "";
	// -- PFADE --------------------------------------------------------------------------------------------------------
	protected static final String BANKEN = "/banken";
	protected static final String BANKVERBINDUNGEN = "/bankverbindungen";
	protected static final String KONTOINHABER = "/kontoinhaber";
	protected static final String PRIVATPERSONEN = "/privatpersonen";
	// -- EINGABEDATEN -------------------------------------------------------------------------------------------------
	protected static final String BEZEICHNUNG = "bezeichnung";
	protected static final String BANK_ID = "bankId";
	protected static final String BENUTZER_ID = "benutzerId";
	protected static final String BIC = "bic";
	protected static final String IBAN = "iban";
	protected static final String NACHNAME = "nachname";
	protected static final String VORNAME = "vorname";

	@BeforeAll
	static void konfiguriere()
	{
		RestAssured.config =
			RestAssuredConfig.config().decoderConfig(DecoderConfig.decoderConfig().defaultContentCharset("UTF-8"));
	}
}
