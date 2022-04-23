package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Betrag;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.model.attribute.Turnus;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.util.function.Function;

@Entity
@Getter
@Table(name = "Einnahmequelle")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Einnahmequelle extends Entitaet
{
	@Serial
	private static final long serialVersionUID = 5466553537615787882L;

	@NonNull
	@Embedded
	private Bezeichnung bezeichnung;

	@NonNull
	@Column(name = "Turnus")
	@Enumerated(EnumType.STRING)
	private Turnus turnus;

	@NonNull
	@Embedded
	private Betrag betrag;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "BenutzerID", nullable = false)
	private Benutzer benutzer;

	public static Validation<Meldungen, Einnahmequelle> aus(
		Bezeichnung bezeichnung,
		Turnus turnus,
		Betrag betrag,
		Benutzer benutzer)
	{
		return Validation.combine(
				validiere(bezeichnung, Meldung.BEZEICHNUNG),
				validiere(turnus, Meldung.TURNUS),
				validiere(betrag, Meldung.BETRAG_LEER),
				validiere(benutzer, Meldung.BENUTZER_LEER))
			.ap(Einnahmequelle::new)
			.bimap(Meldungen::ausSeq, Function.identity());
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("Bezeichnung", bezeichnung)
			.add("Turnus", turnus)
			.add("Betrag", betrag)
			.add("Benutzer", benutzer)
			.toString();
	}
}
