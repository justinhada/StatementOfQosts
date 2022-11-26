package de.justinharder.soq.domain.model;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Transaktion sollte")
class TransaktionSollte extends Testdaten
{
	private Transaktion sut;

	private Validation<Meldungen, Transaktion> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Transaktion.aus(null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BEZEICHNUNG, Meldung.CODE));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Transaktion.aus(BEZEICHNUNG_3, CODE_1);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(BEZEICHNUNG_3),
			() -> assertThat(sut.getCode()).isEqualTo(CODE_1));

		validierung = Transaktion.aus(BEZEICHNUNG_4, CODE_2);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(BEZEICHNUNG_4),
			() -> assertThat(sut.getCode()).isEqualTo(CODE_2));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(TRANSAKTION_1).hasToString(
			"Transaktion{ID=" + TRANSAKTION_1.getId() + ", Bezeichnung=Gutschrift, Code=152}");
	}
}
