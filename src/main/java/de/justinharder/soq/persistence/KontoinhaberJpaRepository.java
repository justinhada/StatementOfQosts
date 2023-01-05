package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.Kontoinhaber;
import de.justinharder.soq.domain.repository.KontoinhaberRepository;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.NonNull;

import javax.enterprise.context.Dependent;

@Dependent
public class KontoinhaberJpaRepository extends JpaRepository<Kontoinhaber> implements KontoinhaberRepository
{
	public KontoinhaberJpaRepository()
	{
		super(Kontoinhaber.class);
	}

	@Override
	public Option<Kontoinhaber> finde(@NonNull Benutzer benutzer, @NonNull Bankverbindung bankverbindung)
	{
		return Try.of(() -> entityManager.createQuery(
					"SELECT kontoinhaber FROM Kontoinhaber kontoinhaber WHERE kontoinhaber.benutzer = :benutzer AND kontoinhaber.bankverbindung = :bankverbindung",
					Kontoinhaber.class)
				.setParameter("benutzer", benutzer)
				.setParameter("bankverbindung", bankverbindung)
				.getSingleResult())
			.toOption();
	}

	@Override
	public boolean istVorhanden(@NonNull Benutzer benutzer, @NonNull Bankverbindung bankverbindung)
	{
		return finde(benutzer, bankverbindung).isDefined();
	}
}
