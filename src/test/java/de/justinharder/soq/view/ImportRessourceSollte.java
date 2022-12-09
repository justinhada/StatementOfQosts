package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.imports.ImportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("ImportRessource sollte")
class ImportRessourceSollte
{
	private ImportService importService;

	@BeforeEach
	void setup()
	{
		importService = mock(ImportService.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertThrows(NullPointerException.class, () -> new ImportRessource(null));
	}
}
