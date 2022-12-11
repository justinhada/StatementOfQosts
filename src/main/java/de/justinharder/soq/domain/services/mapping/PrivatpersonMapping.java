package de.justinharder.soq.domain.services.mapping;

import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.services.dto.GespeichertePrivatperson;
import lombok.NonNull;

import javax.enterprise.context.Dependent;

@Dependent
public class PrivatpersonMapping implements Mapping<Benutzer, GespeichertePrivatperson>
{
	@Override
	public GespeichertePrivatperson mappe(@NonNull Benutzer benutzer)
	{
		return new GespeichertePrivatperson(
			benutzer.getId().getWert().toString(),
			benutzer.getNachname().getWert(),
			benutzer.getVorname().getWert());
	}
}
