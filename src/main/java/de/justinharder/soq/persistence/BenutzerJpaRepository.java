package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.repository.BenutzerRepository;

import javax.enterprise.context.Dependent;

@Dependent
public class BenutzerJpaRepository extends JpaRepository<Benutzer> implements BenutzerRepository
{
	public BenutzerJpaRepository()
	{
		super(Benutzer.class);
	}
}
