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

@DisplayName("Bank sollte")
class BankSollte extends Testdaten
{
	private Bank sut;

	private Validation<Meldungen, Bank> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Bank.aus(null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.BEZEICHNUNG_LEER,
				Meldung.BIC_LEER));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Bank.aus(BEZEICHNUNG_1, BIC_1);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(BEZEICHNUNG_1),
			() -> assertThat(sut.getBic()).isEqualTo(BIC_1));

		validierung = Bank.aus(BEZEICHNUNG_2, BIC_2);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getBezeichnung()).isEqualTo(BEZEICHNUNG_2),
			() -> assertThat(sut.getBic()).isEqualTo(BIC_2));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(BANK_1).hasToString(
			"Bank{ID=" + BANK_1.getId() + ", Bezeichnung=Oldenburgische Landesbank AG, BIC=OLBO DE H2 XXX}");
	}
}
