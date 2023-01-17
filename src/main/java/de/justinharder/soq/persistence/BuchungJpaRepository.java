package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.Buchung;
import de.justinharder.soq.domain.model.Kategorie;
import de.justinharder.soq.domain.model.Umsatz;
import de.justinharder.soq.domain.repository.BuchungRepository;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.NonNull;

import javax.enterprise.context.Dependent;

@Dependent
public class BuchungJpaRepository extends JpaRepository<Buchung> implements BuchungRepository
{
	public BuchungJpaRepository()
	{
		super(Buchung.class);
	}

	@Override
	public Option<Buchung> finde(@NonNull Umsatz umsatz, @NonNull Kategorie kategorie)
	{
		return Try.of(() -> entityManager.createQuery(
					"SELECT buchung FROM Buchung buchung WHERE buchung.umsatz = :umsatz AND buchung.kategorie = :kategorie",
					Buchung.class)
				.setParameter("umsatz", umsatz)
				.setParameter("kategorie", kategorie)
				.getSingleResult())
			.toOption();
	}

	@Override
	public boolean istVorhanden(@NonNull Umsatz umsatz, @NonNull Kategorie kategorie)
	{
		return finde(umsatz, kategorie).isDefined();
	}
}
