package de.justinharder.soq.domain.repository;

import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.attribute.IBAN;
import io.vavr.control.Option;
import lombok.NonNull;

public interface BankverbindungRepository extends Repository<Bankverbindung>
{
	Option<Bankverbindung> finde(@NonNull IBAN iban);

	boolean istVorhanden(@NonNull IBAN iban);
}
