package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Nachname;
import de.justinharder.soq.domain.model.attribute.Vorname;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.io.Serial;
import java.util.function.Function;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
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
				validiere(nachname, Meldung.NACHNAME),
				validiere(vorname, Meldung.VORNAME))
			.ap(Person::new)
			.bimap(Meldungen::ausSeq, Function.identity());
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("Nachname", nachname)
			.add("Vorname", vorname)
			.toString();
	}
}
