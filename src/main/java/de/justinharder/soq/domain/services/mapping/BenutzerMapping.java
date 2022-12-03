package de.justinharder.soq.domain.services.mapping;

import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.services.dto.GespeicherterBenutzer;
import lombok.NonNull;

import javax.enterprise.context.Dependent;

@Dependent
public class BenutzerMapping implements Mapping<Benutzer, GespeicherterBenutzer>
{
	@Override
	public GespeicherterBenutzer mappe(@NonNull Benutzer benutzer)
	{
		return new GespeicherterBenutzer(
			benutzer.getId().getWert().toString(),
			benutzer.getNachname().getWert(),
			benutzer.getVorname().getWert());
	}
}
