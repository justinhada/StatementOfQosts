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
	@JoinColumn(name = "BankverbindungSenderID", nullable = false)
	private Bankverbindung bankverbindungSender;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "BankverbindungEmpfaengerID", nullable = false)
	private Bankverbindung bankverbindungEmpfaenger;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "TransaktionID", nullable = false)
	private Transaktion transaktion;

	public static Validation<Meldungen, Umsatz> aus(
		Datum datum,
		Betrag betrag,
		Verwendungszweck verwendungszweck,
		Bankverbindung bankverbindungSender,
		Bankverbindung bankverbindungEmpfaenger,
		Transaktion transaktion)
	{
		return Validation.combine(
				validiere(datum, Meldung.DATUM),
				validiere(betrag, Meldung.BETRAG_LEER),
				validiere(verwendungszweck, Meldung.VERWENDUNGSZWECK),
				validiere(bankverbindungSender, Meldung.BANKVERBINDUNG),
				validiere(bankverbindungEmpfaenger, Meldung.BANKVERBINDUNG),
				validiere(transaktion, Meldung.TRANSAKTION))
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
			.add("BankverbindungSender", bankverbindungSender)
			.add("BankverbindungEmpfaenger", bankverbindungEmpfaenger)
			.add("Transaktion", transaktion)
			.toString();
	}
}
