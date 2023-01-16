package de.justinharder.soq.persistence;

import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.Umsatz;
import de.justinharder.soq.domain.model.attribute.Betrag;
import de.justinharder.soq.domain.model.attribute.Datum;
import de.justinharder.soq.domain.model.attribute.Verwendungszweck;
import de.justinharder.soq.domain.repository.UmsatzRepository;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.NonNull;

import javax.enterprise.context.Dependent;

@Dependent
public class UmsatzJpaRepository extends JpaRepository<Umsatz> implements UmsatzRepository
{
	private static final String ABFRAGE = """
		SELECT umsatz
		FROM Umsatz umsatz
		WHERE umsatz.datum = :datum
		AND umsatz.betrag = :betrag
		AND umsatz.verwendungszweck = :verwendungszweck
		AND umsatz.bankverbindungAuftraggeber = :bankverbindungAuftraggeber
		AND umsatz.bankverbindungZahlungsbeteiligter = :bankverbindungZahlungsbeteiligter""";

	public UmsatzJpaRepository()
	{
		super(Umsatz.class);
	}

	@Override
	public Option<Umsatz> finde(
		@NonNull Datum datum,
		@NonNull Betrag betrag,
		@NonNull Verwendungszweck verwendungszweck,
		@NonNull Bankverbindung bankverbindungAuftraggeber,
		@NonNull Bankverbindung bankverbindungZahlungsbeteiligter)
	{
		return Try.of(() -> entityManager.createQuery(ABFRAGE, Umsatz.class)
				.setParameter("datum", datum)
				.setParameter("betrag", betrag)
				.setParameter("verwendungszweck", verwendungszweck)
				.setParameter("bankverbindungAuftraggeber", bankverbindungAuftraggeber)
				.setParameter("bankverbindungZahlungsbeteiligter", bankverbindungZahlungsbeteiligter)
				.getSingleResult())
			.toOption();
	}

	@Override
	public boolean istVorhanden(
		@NonNull Datum datum,
		@NonNull Betrag betrag,
		@NonNull Verwendungszweck verwendungszweck,
		@NonNull Bankverbindung bankverbindungAuftraggeber,
		@NonNull Bankverbindung bankverbindungZahlungsbeteiligter)
	{
		return finde(datum, betrag, verwendungszweck, bankverbindungAuftraggeber, bankverbindungZahlungsbeteiligter)
			.isDefined();
	}
}
