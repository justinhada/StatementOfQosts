package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.model.attribute.Turnus;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "Vertrag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Vertrag extends Entitaet
{
	@NonNull
	@Embedded
	private Bezeichnung bezeichnung;

	@NonNull
	@Column(name = "Turnus")
	@Enumerated(EnumType.STRING)
	private Turnus turnus;

	@OneToMany
	@JoinColumn(name = "VertragID")
	private final List<Buchung> buchungen = new ArrayList<>();

	public static Validation<Meldungen, Vertrag> aus(Bezeichnung bezeichnung, Turnus turnus)
	{
		return Validation.combine(
				validiere(bezeichnung, Meldung.BEZEICHNUNG_LEER),
				validiere(turnus, Meldung.TURNUS))
			.ap(Vertrag::new)
			.mapError(Meldungen::aus);
	}

	public void fuegeBuchungHinzu(@NonNull Buchung buchung)
	{
		buchungen.add(buchung);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("Bezeichnung", bezeichnung)
			.add("Turnus", turnus)
			.add("Buchungen", buchungen)
			.toString();
	}
}
