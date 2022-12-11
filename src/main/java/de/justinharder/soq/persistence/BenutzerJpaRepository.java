package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.attribute.Firma;
import de.justinharder.soq.domain.model.attribute.Nachname;
import de.justinharder.soq.domain.model.attribute.Vorname;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.NonNull;

import javax.enterprise.context.Dependent;

@Dependent
public class BenutzerJpaRepository extends JpaRepository<Benutzer> implements BenutzerRepository
{
	public BenutzerJpaRepository()
	{
		super(Benutzer.class);
	}

	@Override
	public Option<Benutzer> finde(@NonNull Nachname nachname, @NonNull Vorname vorname)
	{
		return Try.of(() -> entityManager.createQuery(
					"SELECT benutzer FROM Benutzer benutzer WHERE benutzer.nachname = :nachname AND benutzer.vorname = :vorname",
					Benutzer.class)
				.setParameter("nachname", nachname)
				.setParameter("vorname", vorname)
				.getSingleResult())
			.toOption();
	}

	@Override
	public Option<Benutzer> finde(@NonNull Firma firma)
	{
		return Try.of(() -> entityManager.createQuery(
					"SELECT benutzer FROM Benutzer benutzer WHERE benutzer.firma = :firma",
					Benutzer.class)
				.setParameter("firma", firma)
				.getSingleResult())
			.toOption();
	}

	@Override
	public boolean istVorhanden(@NonNull Nachname nachname, @NonNull Vorname vorname)
	{
		return finde(nachname, vorname).isDefined();
	}

	@Override
	public boolean istVorhanden(@NonNull Firma firma)
	{
		return finde(firma).isDefined();
	}
}
