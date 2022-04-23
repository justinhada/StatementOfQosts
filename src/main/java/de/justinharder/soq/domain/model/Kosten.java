package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "Kosten")
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
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "KostenID")
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
			.add("ID", id)
			.add("Bezeichnung", bezeichnung)
			.add("Kostenpunkte", kostenpunkte)
			.toString();
	}
}
