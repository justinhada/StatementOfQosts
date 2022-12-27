package de.justinharder.soq.domain.services;

import de.justinharder.DtoTestdaten;
import de.justinharder.soq.domain.repository.UmsatzRepository;
import de.justinharder.soq.domain.services.mapping.UmsatzMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@DisplayName("UmsatzService sollte")
class UmsatzServiceSollte extends DtoTestdaten
{
	private UmsatzService sut;

	private UmsatzRepository umsatzRepository;
	private UmsatzMapping umsatzMapping;

	@BeforeEach
	void setup()
	{
		umsatzRepository = mock(UmsatzRepository.class);
		umsatzMapping = mock(UmsatzMapping.class);

		sut = new UmsatzService(umsatzRepository, umsatzMapping);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new UmsatzService(umsatzRepository, null)),
			() -> assertThrows(NullPointerException.class, () -> new UmsatzService(null, umsatzMapping)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		when(umsatzRepository.findeAlle()).thenReturn(List.of(UMSATZ_1, UMSATZ_2));
		when(umsatzMapping.mappe(UMSATZ_1)).thenReturn(GESPEICHERTER_UMSATZ_1);
		when(umsatzMapping.mappe(UMSATZ_2)).thenReturn(GESPEICHERTER_UMSATZ_2);

		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(GESPEICHERTER_UMSATZ_1, GESPEICHERTER_UMSATZ_2);
		verify(umsatzRepository).findeAlle();
		verify(umsatzMapping).mappe(UMSATZ_1);
		verify(umsatzMapping).mappe(UMSATZ_2);
	}
}
