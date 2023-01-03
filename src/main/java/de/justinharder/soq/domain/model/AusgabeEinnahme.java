package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Typ;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;

// TODO: Umbenennen zu "Buchung"
@Entity
@Getter
@Table(name = "AusgabeEinnahme")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AusgabeEinnahme extends Entitaet
{
	@Serial
	private static final long serialVersionUID = 5466553537615787882L;

	@NonNull
	@Column(name = "Typ")
	@Enumerated(EnumType.STRING)
	private Typ typ;

	@NonNull
	@OneToOne(optional = false)
	@JoinColumn(name = "UmsatzID", nullable = false)
	private Umsatz umsatz;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "KategorieID", nullable = false)
	private Kategorie kategorie;

	public static Validation<Meldungen, AusgabeEinnahme> aus(Typ typ, Umsatz umsatz, Kategorie kategorie)
	{
		return Validation.combine(
				validiere(typ, Meldung.ART),
				validiere(umsatz, Meldung.UMSATZ_LEER),
				validiere(kategorie, Meldung.KATEGORIE_LEER))
			.ap(AusgabeEinnahme::new)
			.mapError(Meldungen::aus);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("Typ", typ)
			.add("Umsatz", umsatz)
			.add("Kategorie", kategorie)
			.toString();
	}
}
