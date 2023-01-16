package de.justinharder.soq.domain.services.imports.model;

import com.google.common.base.MoreObjects;
import de.justinharder.soq.domain.model.attribute.Datei;
import de.justinharder.soq.domain.model.attribute.Herausgeber;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Import implements Serializable
{
	@Serial
	private static final long serialVersionUID = -3091286580234810504L;

	@NonNull
	private final Herausgeber herausgeber;

	@NonNull
	private final Datei datei;

	public static Validation<Meldungen, Import> aus(Herausgeber herausgeber, Datei datei)
	{
		return Validation.combine(
				validiere(herausgeber, Meldung.HERAUSGEBER_LEER),
				validiere(datei, Meldung.DATEI))
			.ap(Import::new)
			.mapError(Meldungen::aus)
			.filter(importObjekt -> importObjekt.getHerausgeber().equals(Herausgeber.OLB) && datei.istOLB()
				|| importObjekt.getHerausgeber().equals(Herausgeber.VRB) && !datei.istOLB())
			.getOrElse(Validation.invalid(Meldungen.aus(Meldung.IMPORT_UNGUELTIG)));
	}

	private static <T> Validation<Meldungen, T> validiere(T attribut, Meldung meldung)
	{
		return Option.of(attribut)
			.toValidation(Meldungen.aus(meldung));
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("Herausgeber", herausgeber)
			.add("Datei", datei)
			.toString();
	}
}
