package de.justinharder.soq.domain.repository;

import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.attribute.IBAN;
import de.justinharder.soq.domain.model.attribute.ID;
import io.vavr.control.Option;
import lombok.NonNull;

import java.util.List;

public interface BankverbindungRepository extends Repository<Bankverbindung>
{
	List<Bankverbindung> findeAlle(@NonNull ID bankId);

	Option<Bankverbindung> finde(@NonNull IBAN iban);

	boolean istVorhanden(@NonNull ID bankId);

	boolean istVorhanden(@NonNull IBAN iban);
}
