package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.Kategorie;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.repository.KategorieRepository;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.NonNull;

import javax.enterprise.context.Dependent;

@Dependent
public class KategorieJpaRepository extends JpaRepository<Kategorie> implements KategorieRepository
{
	public KategorieJpaRepository()
	{
		super(Kategorie.class);
	}

	@Override
	public Option<Kategorie> finde(@NonNull Bezeichnung bezeichnung)
	{
		return Try.of(() -> entityManager.createQuery(
					"SELECT kategorie FROM Kategorie kategorie WHERE kategorie.bezeichnung = :bezeichnung",
					Kategorie.class)
				.setParameter("bezeichnung", bezeichnung)
				.getSingleResult())
			.toOption();
	}

	@Override
	public boolean istVorhanden(@NonNull Bezeichnung bezeichnung)
	{
		return finde(bezeichnung).isDefined();
	}
}
