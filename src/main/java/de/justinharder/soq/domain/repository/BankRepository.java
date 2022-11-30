package de.justinharder.soq.domain.repository;

import de.justinharder.soq.domain.model.Bank;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import io.vavr.control.Option;
import lombok.NonNull;

public interface BankRepository extends Repository<Bank>
{
	Option<Bank> finde(@NonNull Bezeichnung bezeichnung);
}
