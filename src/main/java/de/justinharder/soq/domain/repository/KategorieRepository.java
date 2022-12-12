package de.justinharder.soq.domain.repository;

import de.justinharder.soq.domain.model.Kategorie;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import io.vavr.control.Option;
import lombok.NonNull;

public interface KategorieRepository extends Repository<Kategorie>
{
	Option<Kategorie> finde(@NonNull Bezeichnung bezeichnung);

	boolean istVorhanden(@NonNull Bezeichnung bezeichnung);
}
