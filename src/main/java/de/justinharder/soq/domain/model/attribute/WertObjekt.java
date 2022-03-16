package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.model.meldung.Meldung;
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

	protected static Validation<Meldung, String> validiereString(String wert, Meldung meldung)
	{
		return validiere(wert, meldung)
			.flatMap(string -> string.isBlank()
				? Validation.invalid(meldung)
				: Validation.valid(string));
	}

	protected static <U> Validation<Meldung, U> validiere(U wert, Meldung meldung)
	{
		return wert == null
			? Validation.invalid(meldung)
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
