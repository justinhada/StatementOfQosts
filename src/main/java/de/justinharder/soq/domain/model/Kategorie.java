package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;

@Entity
@Getter
@Table(name = "Kategorie")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Kategorie extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -944011812988956775L;

	@NonNull
	@Embedded
	private Bezeichnung bezeichnung;

	public static Validation<Meldungen, Kategorie> aus(Bezeichnung bezeichnung)
	{
		return validiere(bezeichnung, Meldung.BEZEICHNUNG)
			.map(Kategorie::new);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("Bezeichnung", bezeichnung)
			.toString();
	}
}
