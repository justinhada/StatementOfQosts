package de.justinharder.soq.domain.services.mapping;

import de.justinharder.DTOTestdaten;
import de.justinharder.soq.domain.model.Kontoinhaber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BankverbindungMapping sollte")
class BankverbindungMappingSollte extends DTOTestdaten
{
	private static final List<Kontoinhaber> ALLE_KONTOINHABER = List.of(KONTOINHABER_1);

	private BankverbindungMapping sut;

	@BeforeEach
	void setup()
	{
		sut = new BankverbindungMapping();
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.mappe(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.mappeZuAuftraggeber(null, ALLE_KONTOINHABER)),
			() -> assertThrows(NullPointerException.class, () -> sut.mappeZuAuftraggeber(BANKVERBINDUNG_1, null)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.mappeZuZahlungsbeteiligter(null, ALLE_KONTOINHABER)),
			() -> assertThrows(NullPointerException.class,
				() -> sut.mappeZuZahlungsbeteiligter(BANKVERBINDUNG_1, null)));
	}

	@Test
	@DisplayName("mappen")
	void test02()
	{
		assertAll(
			() -> assertThat(sut.mappe(BANKVERBINDUNG_1)).isEqualTo(GESPEICHERTE_BANKVERBINDUNG_1),
			() -> assertThat(sut.mappe(BANKVERBINDUNG_2)).isEqualTo(GESPEICHERTE_BANKVERBINDUNG_2),
			() -> assertThat(sut.mappeZuAuftraggeber(BANKVERBINDUNG_1, List.of(KONTOINHABER_1))).isEqualTo(
				GESPEICHERTER_AUFTRAGGEBER_1),
			() -> assertThat(sut.mappeZuAuftraggeber(BANKVERBINDUNG_2, List.of(KONTOINHABER_2))).isEqualTo(
				GESPEICHERTER_AUFTRAGGEBER_2),
			() -> assertThat(sut.mappeZuAuftraggeber(BANKVERBINDUNG_3, List.of(KONTOINHABER_3))).isEqualTo(
				GESPEICHERTER_AUFTRAGGEBER_3),
			() -> assertThat(sut.mappeZuAuftraggeber(BANKVERBINDUNG_4, List.of(KONTOINHABER_4))).isEqualTo(
				GESPEICHERTER_AUFTRAGGEBER_4),
			() -> assertThat(sut.mappeZuZahlungsbeteiligter(BANKVERBINDUNG_1, List.of(KONTOINHABER_1))).isEqualTo(
				GESPEICHERTER_ZAHLUNGSBETEILIGTER_1),
			() -> assertThat(sut.mappeZuZahlungsbeteiligter(BANKVERBINDUNG_2, List.of(KONTOINHABER_2))).isEqualTo(
				GESPEICHERTER_ZAHLUNGSBETEILIGTER_2),
			() -> assertThat(sut.mappeZuZahlungsbeteiligter(BANKVERBINDUNG_3, List.of(KONTOINHABER_3))).isEqualTo(
				GESPEICHERTER_ZAHLUNGSBETEILIGTER_3),
			() -> assertThat(sut.mappeZuZahlungsbeteiligter(BANKVERBINDUNG_4, List.of(KONTOINHABER_4))).isEqualTo(
				GESPEICHERTER_ZAHLUNGSBETEILIGTER_4));
	}
}
