package de.justinharder.soq.domain.services.mapping;

import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.services.dto.GespeichertesUnternehmen;
import lombok.NonNull;

import javax.enterprise.context.Dependent;

@Dependent
public class UnternehmenMapping implements Mapping<Benutzer, GespeichertesUnternehmen>
{
	@Override
	public GespeichertesUnternehmen mappe(@NonNull Benutzer benutzer)
	{
		return new GespeichertesUnternehmen(
			benutzer.getId().getWert().toString(),
			benutzer.getFirma().getWert());
	}
}
