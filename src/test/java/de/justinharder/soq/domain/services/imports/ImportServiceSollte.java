package de.justinharder.soq.domain.services.imports;

import de.justinharder.ImportTestdaten;
import de.justinharder.soq.domain.repository.UmsatzRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("ImportService sollte")
class ImportServiceSollte extends ImportTestdaten
{
	private ImportService sut;

	private UmsatzRepository umsatzRepository;

	@BeforeEach
	void setup()
	{
		umsatzRepository = mock(UmsatzRepository.class);

		sut = new ImportService(umsatzRepository);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertThrows(NullPointerException.class, () -> new ImportService(null));
	}
}
