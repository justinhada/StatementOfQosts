package de.justinharder.soq.domain.services.dto;

import de.justinharder.DtoTestdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Dto sollte")
abstract class DtoSollte<T extends Dto<T>> extends DtoTestdaten
{
	private T sut;
	private Meldung erfolgMeldung;
	private Meldung fehlerMeldung1;
	private Meldung fehlerMeldung2;

	void setup(T sut, Meldung erfolgMeldung, Meldung fehlerMeldung1, Meldung fehlerMeldung2)
	{
		this.sut = sut;
		this.erfolgMeldung = erfolgMeldung;
		this.fehlerMeldung1 = fehlerMeldung1;
		this.fehlerMeldung2 = fehlerMeldung2;
	}

	@Test
	@DisplayName("erfolgreich sein")
	void test001()
	{
		sut.fuegeMeldungHinzu(erfolgMeldung);
		assertThat(sut.istErfolgreich()).isTrue();
	}

	@Test
	@DisplayName("nicht erfolgreich sein")
	void test002()
	{
		sut.fuegeMeldungHinzu(fehlerMeldung1);
		assertThat(sut.istErfolgreich()).isFalse();
	}

	@Test
	@DisplayName("Meldung hinzufuegen")
	void test003()
	{
		sut.fuegeMeldungHinzu(erfolgMeldung);
		assertAll(
			() -> assertThat(sut.hatMeldungen(erfolgMeldung.schluessel())).isTrue(),
			() -> assertThat(sut.hatMeldungen(fehlerMeldung1.schluessel())).isFalse(),
			() -> assertThat(sut.hatMeldungen(fehlerMeldung2.schluessel())).isFalse(),
			() -> assertThat(sut.getMeldungen(erfolgMeldung.schluessel())).containsExactlyInAnyOrder(erfolgMeldung),
			() -> assertThat(sut.getMeldungen(fehlerMeldung1.schluessel())).isEmpty(),
			() -> assertThat(sut.getMeldungen(fehlerMeldung2.schluessel())).isEmpty());
	}

	@Test
	@DisplayName("Meldungen hinzufuegen")
	void test004()
	{
		sut.fuegeMeldungHinzu(fehlerMeldung1).fuegeMeldungHinzu(fehlerMeldung2);
		assertAll(
			() -> assertThat(sut.hatMeldungen(erfolgMeldung.schluessel())).isFalse(),
			() -> assertThat(sut.hatMeldungen(fehlerMeldung1.schluessel())).isTrue(),
			() -> assertThat(sut.hatMeldungen(fehlerMeldung2.schluessel())).isTrue(),
			() -> assertThat(sut.getMeldungen(erfolgMeldung.schluessel())).isEmpty(),
			() -> assertThat(sut.getMeldungen(fehlerMeldung1.schluessel())).containsExactlyInAnyOrder(fehlerMeldung1),
			() -> assertThat(sut.getMeldungen(fehlerMeldung2.schluessel())).containsExactlyInAnyOrder(fehlerMeldung2));
	}
}
