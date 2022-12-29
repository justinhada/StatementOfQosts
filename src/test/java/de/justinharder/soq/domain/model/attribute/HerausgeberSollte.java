package de.justinharder.soq.domain.model.attribute;

import de.justinharder.Testdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Herausgeber sollte")
class HerausgeberSollte extends Testdaten
{
	private Herausgeber sut;

	private Validation<Meldungen, Herausgeber> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Herausgeber.aus(0);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.HERAUSGEBER_UNGUELTIG));

		validierung = Herausgeber.aus(3);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(Meldung.HERAUSGEBER_UNGUELTIG));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Herausgeber.aus(1);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getCode()).isEqualTo(1),
			() -> assertThat(sut.getWert()).isEqualTo("Oldenburgische Landesbank AG"));

		validierung = Herausgeber.aus(2);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getCode()).isEqualTo(2),
			() -> assertThat(sut.getWert()).isEqualTo("VR BANK Dinklage-Steinfeld eG"));
	}

	@Test
	@DisplayName("sich drucken")
	void test03()
	{
		assertThat(Herausgeber.OLB).hasToString("Herausgeber.OLB(code=1, wert=Oldenburgische Landesbank AG)");
	}
}
