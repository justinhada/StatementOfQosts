package de.justinharder.soq.domain.services;

import de.justinharder.DtoTestdaten;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.services.mapping.BenutzerMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("BenutzerService sollte")
public class BenutzerServiceSollte extends DtoTestdaten
{
	private BenutzerService sut;

	private BenutzerRepository benutzerRepository;
	private BenutzerMapping benutzerMapping;

	@BeforeEach
	void setup()
	{
		benutzerRepository = mock(BenutzerRepository.class);
		benutzerMapping = mock(BenutzerMapping.class);

		sut = new BenutzerService(benutzerRepository, benutzerMapping);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new BenutzerService(benutzerRepository, null)),
			() -> assertThrows(NullPointerException.class, () -> new BenutzerService(null, benutzerMapping)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		when(benutzerRepository.findeAlle()).thenReturn(List.of(BENUTZER_1, BENUTZER_2));
		when(benutzerMapping.mappe(BENUTZER_1)).thenReturn(GESPEICHERTER_BENUTZER_1);
		when(benutzerMapping.mappe(BENUTZER_2)).thenReturn(GESPEICHERTER_BENUTZER_2);

		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(GESPEICHERTER_BENUTZER_1, GESPEICHERTER_BENUTZER_2);
	}
}
