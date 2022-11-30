package de.justinharder.soq.domain.services.mapping;

import de.justinharder.soq.domain.model.Entitaet;
import lombok.NonNull;

public interface Mapping<T extends Entitaet, U>
{
	U mappe(@NonNull T entitaet);
}
