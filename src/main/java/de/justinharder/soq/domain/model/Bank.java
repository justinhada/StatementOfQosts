package de.justinharder.soq.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.BIC;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
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
@Table(name = "Bank")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Bank extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -7862317599521419955L;

	@Setter
	@NonNull
	@Embedded
	private Bezeichnung bezeichnung;

	@NonNull
	@Embedded
	private BIC bic;

	public static Validation<Meldungen, Bank> aus(Bezeichnung bezeichnung, BIC bic)
	{
		return Validation.combine(validiere(bezeichnung, Meldung.BEZEICHNUNG_LEER), validiere(bic, Meldung.BIC_LEER))
			.ap(Bank::new)
			.mapError(Meldungen::aus);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("Bezeichnung", bezeichnung)
			.add("BIC", bic)
			.toString();
	}
}
