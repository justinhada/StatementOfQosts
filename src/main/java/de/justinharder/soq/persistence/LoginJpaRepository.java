package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.Login;
import de.justinharder.soq.domain.model.attribute.Benutzername;
import de.justinharder.soq.domain.repository.LoginRepository;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.NonNull;

import javax.enterprise.context.Dependent;

@Dependent
public class LoginJpaRepository extends JpaRepository<Login> implements LoginRepository
{
	public LoginJpaRepository()
	{
		super(Login.class);
	}

	@Override
	public Option<Login> finde(@NonNull Benutzername benutzername)
	{
		return Try.of(() -> entityManager.createQuery(
					"SELECT login FROM Login login WHERE login.benutzername = :benutzername",
					Login.class)
				.setParameter("benutzername", benutzername)
				.getSingleResult())
			.toOption();
	}

	@Override
	public boolean istVorhanden(@NonNull Benutzername benutzername)
	{
		return finde(benutzername).isDefined();
	}
}
