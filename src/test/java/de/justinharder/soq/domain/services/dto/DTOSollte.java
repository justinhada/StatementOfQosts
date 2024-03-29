package de.justinharder.soq.domain.services.dto;

import de.justinharder.DTOTestdaten;
import de.justinharder.soq.domain.model.meldung.Meldung;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Dto sollte")
abstract class DTOSollte<T extends DTO<T>> extends DTOTestdaten
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
	@DisplayName("null validieren")
	void test001()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.fuegeMeldungHinzu(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.fuegeMeldungenHinzu(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.getMeldungen(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.hatMeldungen(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.fasseZusammen(null)));
	}

	@Test
	@DisplayName("erfolgreich sein")
	void test002()
	{
		sut.fuegeMeldungHinzu(erfolgMeldung);
		assertThat(sut.istErfolgreich()).isTrue();
	}

	@Test
	@DisplayName("nicht erfolgreich sein")
	void test003()
	{
		sut.fuegeMeldungHinzu(fehlerMeldung1);
		assertThat(sut.istErfolgreich()).isFalse();
	}

	@Test
	@DisplayName("Meldung hinzufuegen")
	void test004()
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
	void test005()
	{
		sut.fuegeMeldungHinzu(fehlerMeldung1).fuegeMeldungHinzu(fehlerMeldung2);
		assertAll(
			() -> assertThat(sut.hatMeldungen(erfolgMeldung.schluessel())).isFalse(),
			() -> assertThat(sut.hatMeldungen(fehlerMeldung1.schluessel())).isTrue(),
			() -> assertThat(sut.hatMeldungen(fehlerMeldung2.schluessel())).isTrue(),
			() -> assertThat(sut.getMeldungen(erfolgMeldung.schluessel())).isEmpty(),
			() -> assertThat(sut.getMeldungen(fehlerMeldung1.schluessel())).contains(fehlerMeldung1),
			() -> assertThat(sut.getMeldungen(fehlerMeldung2.schluessel())).contains(fehlerMeldung2));
	}
}
