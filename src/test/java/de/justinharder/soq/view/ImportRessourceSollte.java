package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.imports.ImportService;
import io.quarkus.qute.Template;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("ImportRessource sollte")
class ImportRessourceSollte
{
	private ImportService importService;
	private Template imports;

	@BeforeEach
	void setup()
	{
		importService = mock(ImportService.class);
		imports = mock(Template.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new ImportRessource(null, imports)),
			() -> assertThrows(NullPointerException.class, () -> new ImportRessource(importService, null)));
	}
}
