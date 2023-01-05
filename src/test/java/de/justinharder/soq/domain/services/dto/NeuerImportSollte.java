package de.justinharder.soq.domain.services.dto;

import de.justinharder.soq.domain.model.meldung.Meldung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("NeuerImport sollte")
class NeuerImportSollte extends DTOSollte<NeuerImport>
{
	private static final int HERAUSGEBER = 1;
	private static final byte[] DATEI = "CSV-Datei".getBytes(StandardCharsets.UTF_8);

	@BeforeEach
	void setup()
	{
		super.setup(
			new NeuerImport(HERAUSGEBER, DATEI),
			Meldung.UMSATZ_ERSTELLT,
			Meldung.HERAUSGEBER_UNGUELTIG,
			Meldung.DATEI);
	}

	@Test
	@DisplayName("NoArgsConstructor, Getter und Setter besitzen")
	void test01()
	{
		var sut = new NeuerImport();
		sut.setHerausgeber(HERAUSGEBER);
		sut.setDatei(DATEI);

		assertAll(
			() -> assertThat(sut.getHerausgeber()).isEqualTo(HERAUSGEBER),
			() -> assertThat(sut.getDatei()).isEqualTo(DATEI),
			() -> assertThat(sut.myself()).isEqualTo(sut));
	}
}
