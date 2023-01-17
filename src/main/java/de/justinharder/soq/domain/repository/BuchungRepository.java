package de.justinharder.soq.domain.repository;

import de.justinharder.soq.domain.model.Buchung;
import de.justinharder.soq.domain.model.Kategorie;
import de.justinharder.soq.domain.model.Umsatz;
import io.vavr.control.Option;
import lombok.NonNull;

public interface BuchungRepository extends Repository<Buchung>
{
	Option<Buchung> finde(@NonNull Umsatz umsatz, @NonNull Kategorie kategorie);

	boolean istVorhanden(@NonNull Umsatz umsatz, @NonNull Kategorie kategorie);
}
