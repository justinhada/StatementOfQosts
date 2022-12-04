package de.justinharder.soq.domain.services;

import de.justinharder.DtoTestdaten;
import de.justinharder.soq.domain.repository.BankRepository;
import de.justinharder.soq.domain.repository.BankverbindungRepository;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.services.mapping.BankverbindungMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("BankverbindungService sollte")
class BankverbindungServiceSollte extends DtoTestdaten
{
	private BankverbindungService sut;

	private BankverbindungRepository bankverbindungRepository;
	private BenutzerRepository benutzerRepository;
	private BankRepository bankRepository;
	private BankverbindungMapping bankverbindungMapping;

	@BeforeEach
	void setup()
	{
		bankverbindungRepository = mock(BankverbindungRepository.class);
		benutzerRepository = mock(BenutzerRepository.class);
		bankRepository = mock(BankRepository.class);
		bankverbindungMapping = mock(BankverbindungMapping.class);

		sut = new BankverbindungService(
			bankverbindungRepository,
			benutzerRepository,
			bankRepository,
			bankverbindungMapping);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungService(null, benutzerRepository, bankRepository, bankverbindungMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungService(bankverbindungRepository, null, bankRepository, bankverbindungMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungService(bankverbindungRepository, benutzerRepository, null,
					bankverbindungMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankverbindungService(bankverbindungRepository, benutzerRepository, bankRepository, null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		when(bankverbindungRepository.findeAlle()).thenReturn(List.of(BANKVERBINDUNG_1, BANKVERBINDUNG_2));
		when(bankverbindungMapping.mappe(BANKVERBINDUNG_1)).thenReturn(GESPEICHERTE_BANKVERBINDUNG_1);
		when(bankverbindungMapping.mappe(BANKVERBINDUNG_2)).thenReturn(GESPEICHERTE_BANKVERBINDUNG_2);

		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(GESPEICHERTE_BANKVERBINDUNG_1,
			GESPEICHERTE_BANKVERBINDUNG_2);
	}
}
