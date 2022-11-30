package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.Bank;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.repository.BankRepository;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.NonNull;

import javax.enterprise.context.Dependent;

@Dependent
public class BankJpaRepository extends JpaRepository<Bank> implements BankRepository
{
	public BankJpaRepository()
	{
		super(Bank.class);
	}

	@Override
	public Option<Bank> finde(@NonNull Bezeichnung bezeichnung)
	{
		return Try.of(() -> entityManager.createQuery(
					"SELECT bank FROM Bank bank WHERE bank.bezeichnung = :bezeichnung",
					Bank.class)
				.setParameter("bezeichnung", bezeichnung)
				.getSingleResult())
			.toOption();
	}
}
