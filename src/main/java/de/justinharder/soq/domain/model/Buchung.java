package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;

@Entity
@Getter
@Table(name = "Buchung")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Buchung extends Entitaet
{
	@Serial
	private static final long serialVersionUID = 5466553537615787882L;

	@NonNull
	@OneToOne(optional = false)
	@JoinColumn(name = "UmsatzID", nullable = false)
	private Umsatz umsatz;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "KategorieID", nullable = false)
	private Kategorie kategorie;

	public static Validation<Meldungen, Buchung> aus(Umsatz umsatz, Kategorie kategorie)
	{
		return Validation.combine(
				validiere(umsatz, Meldung.UMSATZ_LEER),
				validiere(kategorie, Meldung.KATEGORIE_LEER))
			.ap(Buchung::new)
			.mapError(Meldungen::aus);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("Umsatz", umsatz)
			.add("Kategorie", kategorie)
			.toString();
	}
}
