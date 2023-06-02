package de.justinharder.soq.domain.services;

import de.justinharder.DTOTestdaten;
import de.justinharder.soq.domain.model.Bank;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BankRepository;
import de.justinharder.soq.domain.repository.BankverbindungRepository;
import de.justinharder.soq.domain.services.dto.GespeicherteBank;
import de.justinharder.soq.domain.services.dto.NeueBank;
import de.justinharder.soq.domain.services.mapping.BankMapping;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("BankService sollte")
class BankServiceSollte extends DTOTestdaten
{
	private BankService sut;

	private BankRepository bankRepository;
	private BankverbindungRepository bankverbindungRepository;
	private BankMapping bankMapping;

	@BeforeEach
	void setup()
	{
		bankRepository = mock(BankRepository.class);
		bankverbindungRepository = mock(BankverbindungRepository.class);
		bankMapping = mock(BankMapping.class);

		sut = new BankService(bankRepository, bankverbindungRepository, bankMapping);
	}

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new BankService(bankRepository, bankverbindungRepository, null)),
			() -> assertThrows(NullPointerException.class, () -> new BankService(bankRepository, null, bankMapping)),
			() -> assertThrows(NullPointerException.class,
				() -> new BankService(null, bankverbindungRepository, bankMapping)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.erstelle(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.aktualisiere(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.loesche(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		when(bankRepository.findeAlle()).thenReturn(List.of(BANK_1, BANK_2));
		when(bankMapping.mappe(BANK_1)).thenReturn(GESPEICHERTE_BANK_1);
		when(bankMapping.mappe(BANK_2)).thenReturn(GESPEICHERTE_BANK_2);

		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(GESPEICHERTE_BANK_1, GESPEICHERTE_BANK_2);
		verify(bankRepository).findeAlle();
		verify(bankMapping).mappe(BANK_1);
		verify(bankMapping).mappe(BANK_2);
	}

	@Test
	@DisplayName("nicht finden, wenn ID leer oder ungültig ist")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.finde(LEER).getMeldungen(Schluessel.BANK))
				.containsExactlyInAnyOrder(Meldung.idLeer(Schluessel.BANK)),
			() -> assertThat(sut.finde("3ef97d60-4a4d-4ef0-b7a6-9c1c3c06a3f3-123").getMeldungen(Schluessel.BANK))
				.containsExactlyInAnyOrder(Meldung.idUngueltig(Schluessel.BANK)));
	}

	@Test
	@DisplayName("nicht finden, wenn ID nicht existiert")
	void test04()
	{
		when(bankRepository.finde(BANK_1.getId())).thenReturn(Option.none());

		assertThat(sut.finde(BANK_1.getId().getWert().toString()).getMeldungen(Schluessel.BANK))
			.containsExactlyInAnyOrder(Meldung.BANK_EXISTIERT_NICHT);
		verify(bankRepository).finde(BANK_1.getId());
	}

	@Test
	@DisplayName("finden, wenn ID existiert")
	void test05()
	{
		when(bankRepository.finde(BANK_1.getId())).thenReturn(Option.of(BANK_1));
		when(bankMapping.mappe(BANK_1)).thenReturn(GESPEICHERTE_BANK_1);

		var ergebnis = sut.finde(BANK_1.getId().getWert().toString());

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BANK)).isEmpty(),
			() -> assertThat(ergebnis.getId()).isEqualTo(GESPEICHERTE_BANK_1.getId()),
			() -> assertThat(ergebnis.getBezeichnung()).isEqualTo(GESPEICHERTE_BANK_1.getBezeichnung()),
			() -> assertThat(ergebnis.getBic()).isEqualTo(GESPEICHERTE_BANK_1.getBic()));
		verify(bankRepository).finde(BANK_1.getId());
		verify(bankMapping).mappe(BANK_1);
	}

	@Test
	@DisplayName("leere Eingabedaten bei Erstellung melden")
	void test06()
	{
		var ergebnis = sut.erstelle(new NeueBank(LEER, LEER));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BEZEICHNUNG)).containsExactlyInAnyOrder(
				Meldung.BEZEICHNUNG_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BIC)).containsExactlyInAnyOrder(Meldung.BIC_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("ungültige BIC bei Erstellung melden")
	void test07()
	{
		var ergebnis = sut.erstelle(new NeueBank(BEZEICHNUNG_1_WERT, "OLBODEH2XXXX"));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BEZEICHNUNG)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BIC)).containsExactlyInAnyOrder(Meldung.BIC_UNGUELTIG),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
	}

	@Test
	@DisplayName("bereits existierende Daten bei Erstellung melden")
	void test08()
	{
		when(bankRepository.istVorhanden(BEZEICHNUNG_1)).thenReturn(true);
		when(bankRepository.istVorhanden(BIC_1)).thenReturn(true);

		var ergebnis = sut.erstelle(new NeueBank(BEZEICHNUNG_1_WERT, BIC_1_WERT));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BEZEICHNUNG)).containsExactlyInAnyOrder(
				Meldung.BEZEICHNUNG_EXISTIERT_BEREITS),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BIC)).containsExactlyInAnyOrder(
				Meldung.BIC_EXISTIERT_BEREITS),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
		verify(bankRepository).istVorhanden(BEZEICHNUNG_1);
		verify(bankRepository).istVorhanden(BIC_1);
	}

	@Test
	@DisplayName("erstellen")
	void test09()
	{
		when(bankRepository.istVorhanden(BEZEICHNUNG_1)).thenReturn(false);
		when(bankRepository.istVorhanden(BIC_1)).thenReturn(false);

		var ergebnis = sut.erstelle(new NeueBank(BEZEICHNUNG_1_WERT, BIC_1_WERT));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BEZEICHNUNG)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BIC)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.BANK_ERSTELLT));
		verify(bankRepository).istVorhanden(BEZEICHNUNG_1);
		verify(bankRepository).istVorhanden(BIC_1);
		verify(bankRepository).speichere(any(Bank.class));
	}

	@Test
	@DisplayName("nicht aktualisieren, wenn ID leer oder ungültig ist")
	void test10()
	{
		assertAll(
			() -> assertThat(sut.aktualisiere(new GespeicherteBank(
					LEER,
					GESPEICHERTE_BANK_1.getBezeichnung(),
					GESPEICHERTE_BANK_1.getBic()))
				.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(Meldung.idLeer(Schluessel.ALLGEMEIN)),
			() -> assertThat(sut.aktualisiere(new GespeicherteBank(
					"3ef97d60-4a4d-4ef0-b7a6-9c1c3c06a3f3-123",
					GESPEICHERTE_BANK_1.getBezeichnung(),
					GESPEICHERTE_BANK_1.getBic()))
				.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.idUngueltig(Schluessel.ALLGEMEIN)));
	}

	@Test
	@DisplayName("nicht aktualisieren, wenn Bank nicht existiert")
	void test11()
	{
		when(bankRepository.finde(BANK_1.getId())).thenReturn(Option.none());

		var ergebnis = sut.aktualisiere(GESPEICHERTE_BANK_1);

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BEZEICHNUNG)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BIC)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.BANK_EXISTIERT_NICHT_ALLGEMEIN));
		verify(bankRepository).finde(BANK_1.getId());
	}

	@Test
	@DisplayName("leere Bezeichnung bei Aktualisierung melden")
	void test12()
	{
		when(bankRepository.finde(BANK_1.getId())).thenReturn(Option.of(BANK_1));

		var ergebnis = sut.aktualisiere(new GespeicherteBank(
			GESPEICHERTE_BANK_1.getId(),
			LEER,
			GESPEICHERTE_BANK_1.getBic()));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BEZEICHNUNG)).containsExactlyInAnyOrder(
				Meldung.BEZEICHNUNG_LEER),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BIC)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
		verify(bankRepository).finde(BANK_1.getId());
	}

	@Test
	@DisplayName("bereits existierende Bezeichnung bei Aktualisierung melden")
	void test13()
	{
		when(bankRepository.finde(BANK_1.getId())).thenReturn(Option.of(BANK_1));
		when(bankRepository.istVorhanden(BANK_2.getBezeichnung())).thenReturn(true);

		var ergebnis = sut.aktualisiere(new GespeicherteBank(
			GESPEICHERTE_BANK_1.getId(),
			GESPEICHERTE_BANK_2.getBezeichnung(),
			GESPEICHERTE_BANK_1.getBic()));

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BEZEICHNUNG)).containsExactlyInAnyOrder(
				Meldung.BEZEICHNUNG_EXISTIERT_BEREITS),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BIC)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).isEmpty());
		verify(bankRepository).finde(BANK_1.getId());
		verify(bankRepository).istVorhanden(BANK_2.getBezeichnung());
	}

	@Test
	@DisplayName("aktualisieren (Bezeichnung bleibt gleich)")
	void test14()
	{
		var gespeicherteBank = new GespeicherteBank(
			GESPEICHERTE_BANK_1.getId(),
			GESPEICHERTE_BANK_1.getBezeichnung(),
			GESPEICHERTE_BANK_1.getBic());
		when(bankRepository.finde(BANK_1.getId())).thenReturn(Option.of(BANK_1));
		when(bankMapping.mappe(BANK_1)).thenReturn(gespeicherteBank);

		var ergebnis = sut.aktualisiere(gespeicherteBank);

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BEZEICHNUNG)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BIC)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.BANK_AKTUALISIERT));
		verify(bankRepository).finde(BANK_1.getId());
		verify(bankRepository).speichere(BANK_1);
		verify(bankMapping).mappe(BANK_1);
	}

	@Test
	@DisplayName("aktualisieren (Bezeichnung ändert sich)")
	void test15()
	{
		var bank = Bank.aus(BEZEICHNUNG_1, BIC_1).get();
		var neueBezeichnung = "Neue Oldenburgische Landesbank AG";
		var gespeicherteBank = new GespeicherteBank(
			bank.getId().getWert().toString(),
			neueBezeichnung,
			bank.getBic().toString());
		when(bankRepository.finde(bank.getId())).thenReturn(Option.of(bank));
		when(bankRepository.istVorhanden(Bezeichnung.aus(neueBezeichnung).get())).thenReturn(false);
		when(bankMapping.mappe(bank)).thenReturn(gespeicherteBank);

		var ergebnis = sut.aktualisiere(gespeicherteBank);

		assertAll(
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BEZEICHNUNG)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.BIC)).isEmpty(),
			() -> assertThat(ergebnis.getMeldungen(Schluessel.ALLGEMEIN)).containsExactlyInAnyOrder(
				Meldung.BANK_AKTUALISIERT));
		verify(bankRepository).finde(bank.getId());
		verify(bankRepository).istVorhanden(Bezeichnung.aus(neueBezeichnung).get());
		verify(bankRepository).speichere(any(Bank.class));
		verify(bankMapping).mappe(bank);
	}

	@Test
	@DisplayName("nicht löschen, wenn Bank nicht existiert")
	void test16()
	{
		when(bankRepository.finde(BANK_1.getId())).thenReturn(Option.none());

		assertThat(sut.loesche(BANK_1.getId().getWert().toString()).getMeldungen(Schluessel.ALLGEMEIN))
			.containsExactlyInAnyOrder(Meldung.BANK_EXISTIERT_NICHT_ALLGEMEIN);
		verify(bankRepository).finde(BANK_1.getId());
	}

	@Test
	@DisplayName("nicht löschen, wenn Bank noch Bankverbindungen hat")
	void test17()
	{
		when(bankRepository.finde(BANK_1.getId())).thenReturn(Option.of(BANK_1));
		when(bankverbindungRepository.istVorhanden(BANK_1.getId())).thenReturn(true);

		assertThat(sut.loesche(BANK_1.getId().getWert().toString()).getMeldungen(Schluessel.ALLGEMEIN))
			.containsExactlyInAnyOrder(Meldung.BANK_NICHT_LOESCHBAR);
		verify(bankRepository).finde(BANK_1.getId());
		verify(bankverbindungRepository).istVorhanden(BANK_1.getId());
	}

	@Test
	@DisplayName("löschen")
	void test18()
	{
		when(bankRepository.finde(BANK_1.getId())).thenReturn(Option.of(BANK_1));
		when(bankverbindungRepository.istVorhanden(BANK_1.getId())).thenReturn(false);

		assertThat(sut.loesche(BANK_1.getId().getWert().toString()).getMeldungen(Schluessel.ALLGEMEIN))
			.containsExactlyInAnyOrder(Meldung.BANK_GELOESCHT);
		verify(bankRepository).finde(BANK_1.getId());
		verify(bankverbindungRepository).istVorhanden(BANK_1.getId());
		verify(bankRepository).loesche(BANK_1);
	}
}
