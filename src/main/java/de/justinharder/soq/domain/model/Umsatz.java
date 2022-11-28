package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Betrag;
import de.justinharder.soq.domain.model.attribute.Datum;
import de.justinharder.soq.domain.model.attribute.Verwendungszweck;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;

@Entity
@Getter
@Table(name = "Umsatz")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Umsatz extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -5789679781555646718L;

	@NonNull
	@Embedded
	private Datum datum;

	@NonNull
	@Embedded
	private Betrag betrag;

	@NonNull
	@Embedded
	private Verwendungszweck verwendungszweck;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "BankverbindungAuftraggeberID", nullable = false)
	private Bankverbindung bankverbindungAuftraggeber;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "BankverbindungZahlungsbeteiligterID", nullable = false)
	private Bankverbindung bankverbindungZahlungsbeteiligter;

	public static Validation<Meldungen, Umsatz> aus(
		Datum datum,
		Betrag betrag,
		Verwendungszweck verwendungszweck,
		Bankverbindung bankverbindungAuftraggeber,
		Bankverbindung bankverbindungZahlungsbeteiligter)
	{
		return Validation.combine(
				validiere(datum, Meldung.DATUM),
				validiere(betrag, Meldung.BETRAG),
				validiere(verwendungszweck, Meldung.VERWENDUNGSZWECK),
				validiere(bankverbindungAuftraggeber, Meldung.BANKVERBINDUNG),
				validiere(bankverbindungZahlungsbeteiligter, Meldung.BANKVERBINDUNG))
			.ap(Umsatz::new)
			.mapError(Meldungen::ausSeq);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("Datum", datum)
			.add("Betrag", betrag)
			.add("Verwendungszweck", verwendungszweck)
			.add("BankverbindungAuftraggeber", bankverbindungAuftraggeber)
			.add("BankverbindungZahlungsbeteiligter", bankverbindungZahlungsbeteiligter)
			.toString();
	}
}
