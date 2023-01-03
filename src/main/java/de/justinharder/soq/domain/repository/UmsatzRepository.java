package de.justinharder.soq.domain.repository;

import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.Umsatz;
import de.justinharder.soq.domain.model.attribute.Betrag;
import de.justinharder.soq.domain.model.attribute.Datum;
import de.justinharder.soq.domain.model.attribute.Verwendungszweck;
import io.vavr.control.Option;
import lombok.NonNull;

public interface UmsatzRepository extends Repository<Umsatz>
{
	Option<Umsatz> finde(
		@NonNull Datum datum,
		@NonNull Betrag betrag,
		@NonNull Verwendungszweck verwendungszweck,
		@NonNull Bankverbindung bankverbindungAuftraggeber,
		@NonNull Bankverbindung bankverbindungZahlungsbeteiligter);
}
