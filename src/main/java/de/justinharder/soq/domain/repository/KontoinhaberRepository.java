package de.justinharder.soq.domain.repository;

import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.Kontoinhaber;
import io.vavr.control.Option;
import lombok.NonNull;

import java.util.List;

public interface KontoinhaberRepository extends Repository<Kontoinhaber>
{
	List<Kontoinhaber> findeAlle(@NonNull Bankverbindung bankverbindung);

	Option<Kontoinhaber> finde(@NonNull Benutzer benutzer, @NonNull Bankverbindung bankverbindung);

	boolean istVorhanden(@NonNull Benutzer benutzer, @NonNull Bankverbindung bankverbindung);
}
