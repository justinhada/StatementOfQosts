package de.justinharder.soq.domain.services.imports.model.csv;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Datei;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CSV implements Serializable
{
	@Serial
	private static final long serialVersionUID = 4028774087955604042L;

	private final Zeilen zeilen;

	public static Validation<Meldungen, CSV> aus(Datei datei)
	{
		return Option.of(datei)
			.toValidation(Meldungen.aus(Meldung.DATEI))
			.map(Zeilen::aus)
			.map(CSV::new);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("Zeilen", zeilen)
			.toString();
	}
}
