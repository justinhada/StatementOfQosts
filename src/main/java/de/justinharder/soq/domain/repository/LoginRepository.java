package de.justinharder.soq.domain.repository;

import de.justinharder.soq.domain.model.Login;
import de.justinharder.soq.domain.model.attribute.Benutzername;
import io.vavr.control.Option;
import lombok.NonNull;

public interface LoginRepository extends Repository<Login>
{
	Option<Login> finde(@NonNull Benutzername benutzername);

	boolean istVorhanden(@NonNull Benutzername benutzername);
}
