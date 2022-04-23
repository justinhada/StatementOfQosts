package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Betrag;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.model.attribute.Datum;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serial;
import java.util.function.Function;

@Entity
@Getter
@Accessors(chain = true)
@Table(name = "Kostenpunkt")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Kostenpunkt extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -7695291132147617901L;

	@NonNull
	@Embedded
	private Bezeichnung bezeichnung;

	@NonNull
	@Embedded
	private Datum datum;

	@NonNull
	@Embedded
	private Betrag betrag;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "BenutzerID", nullable = false)
	private Benutzer benutzer;

	public static Validation<Meldungen, Kostenpunkt> aus(Bezeichnung bezeichnung, Datum datum, Betrag betrag, Benutzer benutzer)
	{
		return Validation.combine(
				validiere(bezeichnung, Meldung.BEZEICHNUNG),
				validiere(datum, Meldung.DATUM),
				validiere(betrag, Meldung.BETRAG_LEER),
				validiere(benutzer, Meldung.BENUTZER_LEER))
			.ap(Kostenpunkt::new)
			.bimap(Meldungen::ausSeq, Function.identity());
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("Bezeichnung", bezeichnung)
			.add("Datum", datum)
			.add("Betrag", betrag)
			.add("Benutzer", benutzer)
			.toString();
	}
}
