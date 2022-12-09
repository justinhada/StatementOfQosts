package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serial;

@Entity
@Getter
@Table(name = "Kontoinhaber")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Kontoinhaber extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -4594520030458433741L;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "BenutzerID", nullable = false)
	private Benutzer benutzer;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "BankverbindungID", nullable = false)
	private Bankverbindung bankverbindung;

	public Validation<Meldungen, Kontoinhaber> aus(Benutzer benutzer, Bankverbindung bankverbindung)
	{
		return Validation.combine(
				validiere(benutzer, Meldung.BENUTZER_LEER),
				validiere(bankverbindung, Meldung.BANKVERBINDUNG_LEER))
			.ap(Kontoinhaber::new)
			.mapError(Meldungen::aus);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("Benutzer", benutzer)
			.add("Bankverbindung", bankverbindung)
			.toString();
	}
}
