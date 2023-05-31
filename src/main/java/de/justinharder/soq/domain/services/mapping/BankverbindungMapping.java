package de.justinharder.soq.domain.services.mapping;

import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.Kontoinhaber;
import de.justinharder.soq.domain.services.dto.GespeicherteBankverbindung;
import de.justinharder.soq.domain.services.dto.GespeicherterAuftraggeber;
import de.justinharder.soq.domain.services.dto.GespeicherterZahlungsbeteiligter;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class BankverbindungMapping implements Mapping<Bankverbindung, GespeicherteBankverbindung>
{
	@Override
	public GespeicherteBankverbindung mappe(@NonNull Bankverbindung bankverbindung)
	{
		return new GespeicherteBankverbindung(
			bankverbindung.getId().getWert().toString(),
			bankverbindung.getIban().toString(),
			bankverbindung.getBank().getBezeichnung().getWert());
	}

	public GespeicherterAuftraggeber mappeZuAuftraggeber(
		@NonNull Bankverbindung bankverbindung,
		@NonNull List<Kontoinhaber> alleKontoinhaber)
	{
		return new GespeicherterAuftraggeber(
			bankverbindung.getId().getWert().toString(),
			bankverbindung.getIban().toString(),
			bankverbindung.getBank().getBezeichnung().getWert(),
			mappeAlleKontoinhaber(alleKontoinhaber));
	}

	public GespeicherterZahlungsbeteiligter mappeZuZahlungsbeteiligter(
		@NonNull Bankverbindung bankverbindung,
		@NonNull List<Kontoinhaber> alleKontoinhaber)
	{
		return new GespeicherterZahlungsbeteiligter(
			bankverbindung.getId().getWert().toString(),
			bankverbindung.getIban().toString(),
			bankverbindung.getBank().getBezeichnung().getWert(),
			mappeAlleKontoinhaber(alleKontoinhaber));
	}

	private static String mappeAlleKontoinhaber(List<Kontoinhaber> alleKontoinhaber)
	{
		return alleKontoinhaber.stream()
			.map(Kontoinhaber::getBenutzer)
			.map(BankverbindungMapping::mappeBenutzer)
			.collect(Collectors.joining(", "));
	}

	private static String mappeBenutzer(Benutzer benutzer)
	{
		return benutzer.istPrivatperson()
			? benutzer.getVorname().getWert() + " " + benutzer.getNachname().getWert()
			: benutzer.getFirma().getWert();
	}
}
