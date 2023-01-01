package de.justinharder.soq.domain.services.imports.model;

import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.services.imports.model.csv.CSV;
import io.vavr.control.Option;
import io.vavr.control.Validation;

import java.io.Serial;
import java.util.ArrayList;

public class UmsatzDaten extends ArrayList<UmsatzDatum>
{
	@Serial
	private static final long serialVersionUID = 9132312866401384922L;

	public static Validation<Meldungen, UmsatzDaten> aus(Import importObjekt)
	{
		Option.of(importObjekt)
			.toValidation(Meldungen.aus(Meldung.IMPORT))
			.map(Import::getDatei)
			.flatMap(CSV::aus);

		return null;
	}
}
