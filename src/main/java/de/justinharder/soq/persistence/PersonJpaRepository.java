package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.Person;
import de.justinharder.soq.domain.repository.PersonRepository;

import javax.enterprise.context.Dependent;

@Dependent
public class PersonJpaRepository extends JpaRepository<Person> implements PersonRepository
{
	public PersonJpaRepository()
	{
		super(Person.class);
	}
}
