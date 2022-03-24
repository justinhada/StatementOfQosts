package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.model.meldung.Meldung;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailAdresse extends WertObjekt<String>
{
	@Serial
	private static final long serialVersionUID = 8834883601481281835L;

	@NonNull
	@Column(name = "E-Mail-Adresse", nullable = false)
	private String wert;

	public static Validation<Meldung, EmailAdresse> aus(String wert)
	{
		return validiereString(wert, Meldung.E_MAIL_ADRESSE_LEER)
			.flatMap(string -> !string.contains("@")
				? Validation.invalid(Meldung.E_MAIL_ADRESSE_UNGUELTIG)
				: Validation.valid(new EmailAdresse(string)));
	}
}
