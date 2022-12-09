package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Art;
import de.justinharder.soq.domain.model.attribute.Firma;
import de.justinharder.soq.domain.model.attribute.Nachname;
import de.justinharder.soq.domain.model.attribute.Vorname;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serial;

@Entity
@Getter
@Table(name = "Benutzer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Benutzer extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -8045099408632256819L;

	@Embedded
	private Nachname nachname;

	@Embedded
	private Vorname vorname;

	@Embedded
	private Firma firma;

	@NonNull
	@Column(name = "Art")
	@Enumerated(EnumType.STRING)
	private Art art;

	private Benutzer(Nachname nachname, Vorname vorname)
	{
		this.nachname = nachname;
		this.vorname = vorname;
		this.art = Art.PRIVATPERSON;
	}

	private Benutzer(Firma firma)
	{
		this.firma = firma;
		this.art = Art.UNTERNEHMEN;
	}

	public static Validation<Meldungen, Benutzer> aus(Nachname nachname, Vorname vorname)
	{
		return Validation.combine(
				validiere(nachname, Meldung.NACHNAME),
				validiere(vorname, Meldung.VORNAME))
			.ap(Benutzer::new)
			.mapError(Meldungen::aus);
	}

	public static Validation<Meldungen, Benutzer> aus(Firma firma)
	{
		return validiere(firma, Meldung.FIRMA)
			.map(Benutzer::new);
	}

	@Override
	public String toString()
	{
		var stringHelper = MoreObjects.toStringHelper(this)
			.add("ID", id);
		if (art.equals(Art.PRIVATPERSON))
		{
			return stringHelper
				.add("Nachname", nachname)
				.add("Vorname", vorname)
				.add("Art", art)
				.toString();
		}
		return stringHelper
			.add("Firma", firma)
			.add("Art", art)
			.toString();
	}
}
