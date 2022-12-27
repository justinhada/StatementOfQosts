package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.Umsatz;
import de.justinharder.soq.domain.repository.UmsatzRepository;

import javax.enterprise.context.Dependent;

@Dependent
public class UmsatzJpaRepository extends JpaRepository<Umsatz> implements UmsatzRepository
{
	public UmsatzJpaRepository()
	{
		super(Umsatz.class);
	}
}
