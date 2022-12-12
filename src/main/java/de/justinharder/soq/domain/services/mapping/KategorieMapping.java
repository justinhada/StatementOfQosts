package de.justinharder.soq.domain.services.mapping;

import de.justinharder.soq.domain.model.Kategorie;
import de.justinharder.soq.domain.services.dto.GespeicherteKategorie;
import lombok.NonNull;

import javax.enterprise.context.Dependent;

@Dependent
public class KategorieMapping implements Mapping<Kategorie, GespeicherteKategorie>
{
	@Override
	public GespeicherteKategorie mappe(@NonNull Kategorie kategorie)
	{
		return new GespeicherteKategorie(
			kategorie.getId().getWert().toString(),
			kategorie.getBezeichnung().getWert());
	}
}
