package de.justinharder.soq.domain.model;

import io.vavr.control.Validation;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import java.io.Serial;
import java.util.Objects;
import java.util.function.Function;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Person extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -8045099408632256819L;

	@NonNull
	private String nachname;

	@NonNull
	private String vorname;

	private Person(String nachname, String vorname)
	{
		super();
		this.nachname = nachname;
		this.vorname = vorname;
	}

	public static Validation<Meldungen, Person> aus(String nachname, String vorname)
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
