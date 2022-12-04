package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.UUIDMapping;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.util.UUID;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ID extends WertObjekt<UUID>
{
	@Serial
	private static final long serialVersionUID = -2765501812381917984L;

	@NonNull
	@Convert(converter = UUIDMapping.class)
	@Column(name = "ID", columnDefinition = "VARCHAR(36)")
	private UUID wert;

	public static ID random()
	{
		return new ID(UUID.randomUUID());
	}

	public static Validation<Meldungen, ID> aus(String wert, Schluessel schluessel)
	{
		return validiereString(wert, Meldung.ID_LEER(schluessel))
			.map(ID::aus)
			.flatMap(uuid -> uuid.toValidation(Meldungen.aus(Meldung.ID_UNGUELTIG(schluessel))))
			.map(ID::new);
	}

	private static Try<UUID> aus(String wert)
	{
		return Try.of(() -> UUID.fromString(wert));
	}
}
