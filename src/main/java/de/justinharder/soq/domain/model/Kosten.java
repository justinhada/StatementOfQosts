package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Kosten extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -944011812988956775L;

	@NonNull
	@Embedded
	private Bezeichnung bezeichnung;

	@NonNull
	@OneToMany
	private final List<Kostenpunkt> kostenpunkte = new ArrayList<>();

	public static Validation<Meldung, Kosten> aus(Bezeichnung bezeichnung)
	{
		return validiere(bezeichnung, Meldung.BEZEICHNUNG)
			.map(Kosten::new);
	}

	public void fuegeKostenpunktHinzu(@NonNull Kostenpunkt kostenpunkt)
	{
		kostenpunkte.add(kostenpunkt);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("Bezeichnung", bezeichnung)
			.add("Kostenpunkte", kostenpunkte)
			.toString();
	}
}
