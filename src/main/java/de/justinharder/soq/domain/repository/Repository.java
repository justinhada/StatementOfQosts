package de.justinharder.soq.domain.repository;

import de.justinharder.soq.domain.model.Entitaet;
import de.justinharder.soq.domain.model.attribute.ID;
import io.vavr.control.Option;
import lombok.NonNull;

import java.util.List;

public interface Repository<T extends Entitaet>
{
	List<T> findeAlle();

	Option<T> finde(@NonNull ID id);

	void speichere(@NonNull T entitaet);

	void loesche(@NonNull T entitaet);
}
