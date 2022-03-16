package de.justinharder.soq.domain.model;

import de.justinharder.soq.domain.model.attribute.Betrag;
import de.justinharder.soq.domain.model.attribute.Datum;
import de.justinharder.soq.domain.model.attribute.Posten;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import io.vavr.control.Validation;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serial;
import java.util.Objects;
import java.util.function.Function;

@Entity
@Getter
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Kostenpunkt extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -7695291132147617901L;

	@NonNull
	@Embedded
	private Posten posten;

	@NonNull
	@Embedded
	private Datum datum;

	@NonNull
	@Embedded
	private Betrag betrag;

	@NonNull
	@ManyToOne
	private Person person;

	public static Validation<Meldungen, Kostenpunkt> aus(Posten posten, Datum datum, Betrag betrag, Person person, String lol)
	{
		return Validation.combine(
				validiere(posten, Schluessel.POSTEN, "Der Posten darf nicht leer sein!"),
				validiere(datum, Schluessel.DATUM, "Das Datum darf nicht leer sein!"),
				validiere(betrag, Schluessel.BETRAG, "Der Betrag darf nicht leer sein!"),
				validiere(person, Schluessel.PERSON, "Der Vorname darf nicht leer sein!"))
			.ap(Kostenpunkt::new)
			.bimap(Meldungen::ausSeq, Function.identity());
	}

	@Override
	public String toString()
	{
		return Objects.toString(this);
	}
}
