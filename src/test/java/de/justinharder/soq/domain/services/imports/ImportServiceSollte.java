package de.justinharder.soq.domain.services.imports;

import de.justinharder.DTOTestdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.UmsatzRepository;
import de.justinharder.soq.domain.services.dto.NeuerImport;
import de.justinharder.soq.domain.services.imports.erzeugung.UmsatzErzeugung;
import io.vavr.control.Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("ImportService sollte")
class ImportServiceSollte extends DTOTestdaten
{
	private ImportService sut;

	private UmsatzRepository umsatzRepository;
	private UmsatzErzeugung umsatzErzeugung;

	@BeforeEach
	void setup()
	{
		umsatzRepository = mock(UmsatzRepository.class);
		umsatzErzeugung = mock(UmsatzErzeugung.class);

		sut = new ImportService(umsatzRepository, umsatzErzeugung);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new ImportService(null, umsatzErzeugung)),
			() -> assertThrows(NullPointerException.class, () -> new ImportService(umsatzRepository, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.importiere(null)));
	}

	@Test
	@DisplayName("ungültige Eingabedaten melden")
	void test02()
	{
		var neuerImport = new NeuerImport("0", "".getBytes(StandardCharsets.UTF_8));
		var ergebnis = sut.importiere(neuerImport);

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.HERAUSGEBER)).containsExactlyInAnyOrder(
				Meldung.HERAUSGEBER_UNGUELTIG),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.DATEI)).containsExactlyInAnyOrder(Meldung.DATEI),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("importieren")
	void test03()
	{
		when(umsatzErzeugung.findeOderErzeuge(UMSATZ_DATUM_AUS_OLB)).thenReturn(Validation.valid(UMSATZ));

		var ergebnis = sut.importiere(NEUER_IMPORT);

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.HERAUSGEBER)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.DATEI)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.UMSATZ_ERSTELLT));
		verify(umsatzErzeugung).findeOderErzeuge(UMSATZ_DATUM_AUS_OLB);
	}
}
