package de.justinharder.soq.domain.services.imports.model;

import de.justinharder.ImportTestdaten;
import de.justinharder.soq.domain.model.attribute.Herausgeber;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Import sollte")
class ImportSollte extends ImportTestdaten
{
	private Import sut;

	private Validation<Meldungen, Import> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Import.aus(null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.HERAUSGEBER_LEER,
				Meldung.DATEI));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Import.aus(Herausgeber.OLB, DATEI_1);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getHerausgeber()).isEqualTo(Herausgeber.OLB),
			() -> assertThat(sut.getDatei()).isEqualTo(DATEI_1));

		validierung = Import.aus(Herausgeber.VRB, DATEI_2);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getHerausgeber()).isEqualTo(Herausgeber.VRB),
			() -> assertThat(sut.getDatei()).isEqualTo(DATEI_2));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(IMPORT_1).hasToString(
			"Import{Herausgeber=Herausgeber.OLB(code=1, wert=Oldenburgische Landesbank AG), Datei=Inhaberkonto;Buchungsdatum;Valuta;Empfaenger/Auftraggeber;IBAN;BIC;Verwendungszweck;Betrag;Waehrung;Kundenreferenz;Bankreferenz;Primatnota;Transaktions-Code;Transaktions-Text\n" +
				"DE87280200504008357800;31.10.2022;31.10.2022;Laura Tiemerding;DE28280651080012888000;GENODEF1DIK;Wohnungsmiete;447,48;EUR;NONREF;;0004770;152;DA-GUTSCHR\n}");
	}
}
