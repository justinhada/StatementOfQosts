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
		return validiere(wert, Meldung.DATUM)
			.map(Datum::new);
	}

	@Override
	public String toString()
	{
		return wert.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	}
}
