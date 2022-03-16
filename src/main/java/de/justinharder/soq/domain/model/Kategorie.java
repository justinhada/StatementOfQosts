package de.justinharder.soq.domain.model;

import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Kategorie extends Entitaet
{
	@NonNull
	@Embedded
	private Bezeichnung bezeichnung;

	public static Validation<Meldung, Bezeichnung> aus(Bezeichnung bezeichnung)
	{
		return validiere(bezeichnung, Schluessel.BEZEICHNUNG, "Die Bezeichnung darf nicht leer sein!");
	}

	@Override
	public String toString()
	{
		return Objects.toString(this);
	}
}
