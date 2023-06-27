package de.justinharder.soq.domain.services.mapping;

import de.justinharder.soq.domain.model.Kontoinhaber;
import de.justinharder.soq.domain.model.attribute.Art;
import de.justinharder.soq.domain.services.dto.GespeicherterKontoinhaber;
import lombok.NonNull;

import javax.enterprise.context.Dependent;

@Dependent
public class KontoinhaberMapping implements Mapping<Kontoinhaber, GespeicherterKontoinhaber>
{
	@Override
	public GespeicherterKontoinhaber mappe(@NonNull Kontoinhaber kontoinhaber)
	{
		var benutzer = kontoinhaber.getBenutzer();
		return new GespeicherterKontoinhaber(
			kontoinhaber.getId().getWert().toString(),
			benutzer.getArt().equals(Art.PRIVATPERSON)
				? benutzer.getVorname().getWert() + " " + benutzer.getNachname().getWert()
				: benutzer.getFirma().getWert(),
			kontoinhaber.getBankverbindung().getIban().toString(),
			kontoinhaber.getBankverbindung().getBank().getBezeichnung().getWert());
	}
}
