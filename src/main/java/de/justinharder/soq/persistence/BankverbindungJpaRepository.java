package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.attribute.IBAN;
import de.justinharder.soq.domain.repository.BankverbindungRepository;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.NonNull;

import javax.enterprise.context.Dependent;

@Dependent
public class BankverbindungJpaRepository extends JpaRepository<Bankverbindung> implements BankverbindungRepository
{
	public BankverbindungJpaRepository()
	{
		super(Bankverbindung.class);
	}

	@Override
	public Option<Bankverbindung> finde(@NonNull IBAN iban)
	{
		return Try.of(() -> entityManager.createQuery(
					"SELECT bankverbindung FROM Bankverbindung bankverbindung WHERE bankverbindung.iban = :iban",
					Bankverbindung.class)
				.setParameter("iban", iban)
				.getSingleResult())
			.toOption();
	}

	@Override
	public boolean istVorhanden(@NonNull IBAN iban)
	{
		return finde(iban).isDefined();
	}
}
