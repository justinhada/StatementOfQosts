package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.repository.BankverbindungRepository;

import javax.enterprise.context.Dependent;

@Dependent
public class BankverbindungJpaRepository extends JpaRepository<Bankverbindung> implements BankverbindungRepository
{
	public BankverbindungJpaRepository()
	{
		super(Bankverbindung.class);
	}
}
