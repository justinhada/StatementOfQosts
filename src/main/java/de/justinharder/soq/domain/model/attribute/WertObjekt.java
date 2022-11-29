package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Option;
import io.vavr.control.Validation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import static java.util.function.Predicate.not;

public abstract class WertObjekt<T> implements Serializable, Comparable<WertObjekt<T>>
{
	@Serial
	private static final long serialVersionUID = -3153085157460251690L;

	public abstract T getWert();

	protected static <U> Validation<Meldungen, U> validiere(U wert, Meldung meldung)
	{
		return Option.of(wert)
			.toValidation(Meldungen.aus(meldung));
	}

	protected static Validation<Meldungen, String> validiereString(String wert, Meldung meldung)
	{
		return Option.of(wert)
			.filter(not(String::isBlank))
			.toValidation(Meldungen.aus(meldung));
	}

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
