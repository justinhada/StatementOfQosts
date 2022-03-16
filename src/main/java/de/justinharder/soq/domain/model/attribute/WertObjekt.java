package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.model.meldung.Ebene;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import io.vavr.control.Validation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public abstract class WertObjekt<T> implements Serializable, Comparable<WertObjekt<T>>
{
	@Serial
	private static final long serialVersionUID = -3153085157460251690L;

	public abstract T getWert();

	@Override
	@SuppressWarnings("unchecked")
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
		WertObjekt<T> that = (WertObjekt<T>) o;
		return Objects.equals(getWert(), that.getWert());
	}

	protected static Validation<Meldung, String> validiereString(String wert, Schluessel schluessel, String text)
	{
		return validiere(wert, schluessel, text)
			.flatMap(string -> string.isBlank()
				? Validation.invalid(new Meldung(schluessel, Ebene.FEHLER, text))
				: Validation.valid(string));
	}

	protected static <U> Validation<Meldung, U> validiere(U wert, Schluessel schluessel, String text)
	{
		return wert == null
			? Validation.invalid(new Meldung(schluessel, Ebene.FEHLER, text))
			: Validation.valid(wert);
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(getWert());
	}

	@Override
	@SuppressWarnings("unchecked")
	public int compareTo(WertObjekt<T> o)
	{
		return ((Comparable<T>) getWert()).compareTo(o.getWert());
	}

	@Override
	public String toString()
	{
		return getWert().toString();
	}
}
