package de.justinharder.soq.domain.services.dto;

import de.justinharder.DtoTestdaten;
import lombok.NonNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("GespeicherteBankverbindung sollte")
class GespeicherteBankverbindungSollte extends DtoTestdaten
{
	private static final String ID = BANKVERBINDUNG_1.getId().getWert().toString();
	private static final @NonNull String IBAN = BANKVERBINDUNG_1.getIban().getWert();
	private static final @NonNull String NACHNAME = BANKVERBINDUNG_1.getBenutzer().getNachname().getWert();
	private static final @NonNull String VORNAME = BANKVERBINDUNG_1.getBenutzer().getVorname().getWert();
	private static final @NonNull String BANK = BANKVERBINDUNG_1.getBank().getBezeichnung().getWert();

	@Test
	@DisplayName("RequiredArgsConstructor und Getter besitzen")
	void test01()
	{
		var sut = new GespeicherteBankverbindung(ID, IBAN, NACHNAME, VORNAME, BANK);

		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(ID),
			() -> assertThat(sut.getIban()).isEqualTo(IBAN),
			() -> assertThat(sut.getNachname()).isEqualTo(NACHNAME),
			() -> assertThat(sut.getVorname()).isEqualTo(VORNAME),
			() -> assertThat(sut.getBank()).isEqualTo(BANK));
	}
}
