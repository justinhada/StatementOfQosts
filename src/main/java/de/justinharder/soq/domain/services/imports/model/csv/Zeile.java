package de.justinharder.soq.domain.services.imports.model.csv;

import com.google.common.base.MoreObjects;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.stream.Stream;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class Zeile implements Serializable
{
	@Serial
	private static final long serialVersionUID = -7797571939942999455L;

	private final Spalten spalten;

	public static Zeile aus(String zeile)
	{
		return new Zeile(new Spalten(Stream.of(zeile.split(";"))
			.map(Spalte::new)
			.toList()));
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("Spalten", spalten)
			.toString();
	}
}
