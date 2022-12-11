package de.justinharder.soq.domain.services;

import de.justinharder.DtoTestdaten;
import de.justinharder.soq.domain.repository.BankverbindungRepository;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.repository.KontoinhaberRepository;
import de.justinharder.soq.domain.services.mapping.KontoinhaberMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DisplayName("KontoinhaberService sollte")
class KontoinhaberServiceSollte extends DtoTestdaten
{
	private KontoinhaberService sut;

	private KontoinhaberRepository kontoinhaberRepository;
	private BenutzerRepository benutzerRepository;
	private BankverbindungRepository bankverbindungRepository;
	private KontoinhaberMapping kontoinhaberMapping;

	@BeforeEach
	void setup()
	{
		kontoinhaberRepository = mock(KontoinhaberRepository.class);
		benutzerRepository = mock(BenutzerRepository.class);
		bankverbindungRepository = mock(BankverbindungRepository.class);
		kontoinhaberMapping = mock(KontoinhaberMapping.class);

		sut = new KontoinhaberService(
			kontoinhaberRepository,
			benutzerRepository,
			bankverbindungRepository,
			kontoinhaberMapping);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new KontoinhaberService(null, benutzerRepository, bankverbindungRepository, kontoinhaberMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new KontoinhaberService(kontoinhaberRepository, null, bankverbindungRepository,
					kontoinhaberMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new KontoinhaberService(kontoinhaberRepository, benutzerRepository, null, kontoinhaberMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new KontoinhaberService(kontoinhaberRepository, benutzerRepository, bankverbindungRepository,
					null)),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelle(null)));
	}
}
