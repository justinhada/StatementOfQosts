package de.justinharder.soq.domain.model;

import de.justinharder.soq.domain.model.attribute.Nachname;
import de.justinharder.soq.domain.model.attribute.Vorname;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.io.Serial;
import java.util.Objects;
import java.util.function.Function;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Person extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -8045099408632256819L;

	@NonNull
	@Embedded
	private Nachname nachname;

	@NonNull
	@Embedded
	private Vorname vorname;

	public static Validation<Meldungen, Person> aus(Nachname nachname, Vorname vorname)
	{
		return Validation.combine(
				validiereAttribut(nachname, "nachname", "Der Nachname darf nicht leer sein!"),
				validiereAttribut(vorname, "vorname", "Der Vorname darf nicht leer sein!"))
			.ap(Person::new)
			.bimap(Meldungen::ausSeq, Function.identity());
	}

	@Override
	public String toString()
	{
		return Objects.toString(this);
	}
}
