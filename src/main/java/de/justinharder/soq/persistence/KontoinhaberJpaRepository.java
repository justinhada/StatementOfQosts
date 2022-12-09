package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.Kontoinhaber;
import de.justinharder.soq.domain.repository.KontoinhaberRepository;

import javax.enterprise.context.Dependent;

@Dependent
public class KontoinhaberJpaRepository extends JpaRepository<Kontoinhaber> implements KontoinhaberRepository
{
	public KontoinhaberJpaRepository()
	{
		super(Kontoinhaber.class);
	}
}
