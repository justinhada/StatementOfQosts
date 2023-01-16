package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Datum extends WertObjekt<LocalDate>
{
	@Serial
	private static final long serialVersionUID = 5066170286071040307L;

	@NonNull
	@Column(name = "Datum", nullable = false)
	private LocalDate wert;

	public static Validation<Meldungen, Datum> aus(LocalDate wert)
	{
		return validiere(wert, Meldung.DATUM_LEER)
			.map(Datum::new);
	}

	public static Validation<Meldungen, Datum> aus(String wert)
	{
		return validiereString(wert, Meldung.DATUM_LEER)
			.flatMap(string -> {
				try
				{
					return Validation.valid(LocalDate.parse(string, DateTimeFormatter.ISO_DATE));
				}
				catch (DateTimeParseException e)
				{
					return Validation.invalid(Meldungen.aus(Meldung.DATUM_UNGUELTIG));
				}
			})
			.flatMap(Datum::aus);
	}

	@Override
	public String toString()
	{
		return wert.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	}
}
