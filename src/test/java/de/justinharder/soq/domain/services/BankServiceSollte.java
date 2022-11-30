package de.justinharder.soq.domain.services;

import de.justinharder.DtoTestdaten;
import de.justinharder.soq.domain.repository.BankRepository;
import de.justinharder.soq.domain.services.mapping.BankMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("BankService sollte")
class BankServiceSollte extends DtoTestdaten
{
	private BankService sut;

	private BankRepository bankRepository;
	private BankMapping bankMapping;

	@BeforeEach
	void setup()
	{
		bankRepository = mock(BankRepository.class);
		bankMapping = mock(BankMapping.class);

		sut = new BankService(bankRepository, bankMapping);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new BankService(bankRepository, null)),
			() -> assertThrows(NullPointerException.class, () -> new BankService(null, bankMapping)),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelle(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		when(bankRepository.findeAlle()).thenReturn(List.of(BANK_1, BANK_2));
		when(bankMapping.mappe(BANK_1)).thenReturn(GESPEICHERTE_BANK_1);
		when(bankMapping.mappe(BANK_2)).thenReturn(GESPEICHERTE_BANK_2);

		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(GESPEICHERTE_BANK_1, GESPEICHERTE_BANK_2);
	}

	@Test
	@DisplayName("erstellen")
	void test03()
	{
		// TODO: Testen
	}
}
