package de.justinharder.soq.domain.services.imports;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ImportService sollte")
class ImportServiceSollte
{
	private ImportService sut;

	@BeforeEach
	void setup()
	{
		sut = new ImportService();
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{

	}
}
