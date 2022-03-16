package de.justinharder.soq.domain.model;

import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.model.meldung.Ebene;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import io.vavr.control.Validation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@MappedSuperclass
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Entitaet implements Serializable
{
	@Serial
	private static final long serialVersionUID = 1328988081485601075L;

	@NonNull
	@EmbeddedId
	private final ID id;

	protected Entitaet()
	{
		this.id = new ID();
	}

	protected static <T> Validation<Meldung, T> validiere(T attribut, Schluessel schluessel, String text)
	{
		return attribut == null
			? Validation.invalid(new Meldung(schluessel, Ebene.FEHLER, text))
			: Validation.valid(attribut);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		Entitaet entitaet = (Entitaet) o;
		return Objects.equals(id, entitaet.id);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}

	public abstract String toString();
}
