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
import javax.persistence.Table;
import java.io.Serial;

@Entity
@Getter
@Table(name = "Benutzer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Benutzer extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -8045099408632256819L;

	@NonNull
	@Embedded
	private Nachname nachname;

	@NonNull
	@Embedded
	private Vorname vorname;

	public static Validation<Meldungen, Benutzer> aus(Nachname nachname, Vorname vorname)
	{
		return Validation.combine(
				validiere(nachname, Meldung.NACHNAME),
				validiere(vorname, Meldung.VORNAME))
			.ap(Benutzer::new)
			.mapError(Meldungen::aus);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("Nachname", nachname)
			.add("Vorname", vorname)
			.toString();
	}
}
