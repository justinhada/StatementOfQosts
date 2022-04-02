package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.Entitaet;
import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.repository.Repository;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class JpaRepository<T extends Entitaet> implements Repository<T>
{
	@PersistenceContext
	protected EntityManager entityManager;

	private final Class<T> entitaet;

	@Override
	public List<T> findeAlle()
	{
		var criteriaQuery = entityManager.getCriteriaBuilder().createQuery(entitaet);
		return entityManager.createQuery(criteriaQuery.select(criteriaQuery.from(entitaet))).getResultList();
	}

	@Override
	public Option<T> finde(@NonNull ID id)
	{
		return Option.of(entityManager.find(entitaet, id));
	}

	@Override
	public void speichere(@NonNull T entitaet)
	{
		entityManager.persist(entitaet);
	}

	@Override
	public void loesche(@NonNull T entitaet)
	{
		entityManager.remove(entitaet);
	}
}
