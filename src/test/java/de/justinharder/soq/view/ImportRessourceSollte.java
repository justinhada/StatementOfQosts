package de.justinharder.soq.view;

import de.justinharder.soq.TestSollte;
import de.justinharder.soq.domain.services.dto.NeuerImport;
import de.justinharder.soq.domain.services.imports.ImportService;
import de.justinharder.soq.view.theme.ThemeRessource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

@DisplayName("ImportRessource sollte")
class ImportRessourceSollte extends TestSollte
{
	private ThemeRessource themeRessource;
	private ImportService importService;
	private NeuerImport neuerImport;

	@BeforeEach
	void setup()
	{
		themeRessource = mock(ThemeRessource.class);
		importService = mock(ImportService.class);
		neuerImport = mock(NeuerImport.class);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		validiereNull(
			() -> new ImportRessource(null, importService, neuerImport),
			() -> new ImportRessource(themeRessource, null, neuerImport),
			() -> new ImportRessource(themeRessource, importService, null));
	}
}
