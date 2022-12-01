package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.IBAN;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;

@Entity
@Getter
@Table(name = "Bankverbindung")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Bankverbindung extends Entitaet
{
	@Serial
	private static final long serialVersionUID = 3058329259781529242L;

	@NonNull
	@Embedded
	private IBAN iban;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "BenutzerID", nullable = false)
	private Benutzer benutzer;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "BankID", nullable = false)
	private Bank bank;

	public static Validation<Meldungen, Bankverbindung> aus(IBAN iban, Benutzer benutzer, Bank bank)
	{
		return Validation.combine(
				validiere(iban, Meldung.IBAN_LEER),
				validiere(benutzer, Meldung.BENUTZER_LEER),
				validiere(bank, Meldung.BANK_LEER))
			.ap(Bankverbindung::new)
			.mapError(Meldungen::aus);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("IBAN", iban)
			.add("Benutzer", benutzer)
			.add("Bank", bank)
			.toString();
	}
}
