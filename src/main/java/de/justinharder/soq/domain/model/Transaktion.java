package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.model.attribute.Code;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;

@Entity
@Getter
@Table(name = "Transaktion")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Transaktion extends Entitaet
{
	@Serial
	private static final long serialVersionUID = 6306869080013554149L;

	@NonNull
	@Embedded
	private Bezeichnung bezeichnung;

	@NonNull
	@Embedded
	private Code code;

	public static Validation<Meldungen, Transaktion> aus(Bezeichnung bezeichnung, Code code)
	{
		return Validation.combine(
				validiere(bezeichnung, Meldung.BEZEICHNUNG),
				validiere(code, Meldung.Code))
			.ap(Transaktion::new)
			.mapError(Meldungen::ausSeq);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("Bezeichnung", bezeichnung)
			.add("Code", code)
			.toString();
	}
}
