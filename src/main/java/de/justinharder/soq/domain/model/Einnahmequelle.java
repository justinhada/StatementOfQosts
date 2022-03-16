package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Betrag;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.model.attribute.Turnus;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.util.function.Function;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Einnahmequelle extends Entitaet
{
	@Serial
	private static final long serialVersionUID = 5466553537615787882L;

	@NonNull
	@Embedded
	private Bezeichnung bezeichnung;

	@NonNull
	@Enumerated(EnumType.STRING)
	private Turnus turnus;

	@NonNull
	@Embedded
	private Betrag betrag;

	@NonNull
	@ManyToOne
	private Person person;

	public static Validation<Meldungen, Einnahmequelle> aus(
		Bezeichnung bezeichnung,
		Turnus turnus,
		Betrag betrag,
		Person person)
	{
		return Validation.combine(
				validiere(bezeichnung, Meldung.BEZEICHNUNG),
				validiere(turnus, Meldung.TURNUS),
				validiere(betrag, Meldung.BETRAG_LEER),
				validiere(person, Meldung.PERSON))
			.ap(Einnahmequelle::new)
			.bimap(Meldungen::ausSeq, Function.identity());
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("Bezeichnung", bezeichnung)
			.add("Turnus", turnus)
			.add("Betrag", betrag)
			.add("Person", person)
			.toString();
	}
}
