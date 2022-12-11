package de.justinharder.soq.domain.repository;

import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.attribute.Firma;
import de.justinharder.soq.domain.model.attribute.Nachname;
import de.justinharder.soq.domain.model.attribute.Vorname;
import io.vavr.control.Option;
import lombok.NonNull;

public interface BenutzerRepository extends Repository<Benutzer>
{
	Option<Benutzer> finde(@NonNull Nachname nachname, @NonNull Vorname vorname);

	Option<Benutzer> finde(@NonNull Firma firma);

	boolean istVorhanden(@NonNull Nachname nachname, @NonNull Vorname vorname);

	boolean istVorhanden(@NonNull Firma firma);
}
