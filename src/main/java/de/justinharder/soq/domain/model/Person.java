package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Nachname;
import de.justinharder.soq.domain.model.attribute.Vorname;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.util.function.Function;

@Entity
@Getter
@Table(name = "Person")
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

	public static Validation<Meldungen, Person> aus(String nachname, String vorname)
	{
		return Validation.combine(Nachname.aus(nachname), Vorname.aus(vorname))
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
