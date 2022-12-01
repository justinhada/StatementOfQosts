package de.justinharder.soq.view;

import io.restassured.RestAssured;
import io.restassured.config.DecoderConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.jupiter.api.BeforeAll;

public class RessourceSollte
{
	@BeforeAll
	static void konfiguriere()
	{
		RestAssured.config =
			RestAssuredConfig.config().decoderConfig(DecoderConfig.decoderConfig().defaultContentCharset("UTF-8"));
	}
}
