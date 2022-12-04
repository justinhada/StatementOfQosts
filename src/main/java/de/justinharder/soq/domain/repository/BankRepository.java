package de.justinharder.soq.domain.repository;

import de.justinharder.soq.domain.model.Bank;
import de.justinharder.soq.domain.model.attribute.BIC;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import io.vavr.control.Option;
import lombok.NonNull;

public interface BankRepository extends Repository<Bank>
{
	Option<Bank> finde(@NonNull Bezeichnung bezeichnung);

	Option<Bank> finde(@NonNull BIC bic);

	boolean istVorhanden(@NonNull Bezeichnung bezeichnung);

	boolean istVorhanden(@NonNull BIC bic);
}
