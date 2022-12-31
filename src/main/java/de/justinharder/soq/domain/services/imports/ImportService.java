package de.justinharder.soq.domain.services.imports;

import de.justinharder.soq.domain.model.attribute.Datei;
import de.justinharder.soq.domain.model.attribute.Herausgeber;
import de.justinharder.soq.domain.repository.UmsatzRepository;
import de.justinharder.soq.domain.services.dto.NeuerImport;
import de.justinharder.soq.domain.services.imports.model.Import;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class ImportService
{
	@NonNull
	private final UmsatzRepository umsatzRepository;

	@Inject
	public ImportService(@NonNull UmsatzRepository umsatzRepository)
	{
		this.umsatzRepository = umsatzRepository;
	}

	public NeuerImport importiere(@NonNull NeuerImport neuerImport)
	{
		/*
			1 Herausgeber validieren.
			2 Datei validieren.
			3 Import aus Herausgeber & Datei erstellen.
			4 CSV aus Datei erstellen.
			5 UmsatzDaten aus CSV - abhängig vom Herausgeber - erstellen.
			  a OLB-/VRB-UmsatzDatum aus Zeile erstellen.
			  b UmsatzDatum aus OLB-/VRB-UmsatzDatum erstellen.
			  c UmsatzDaten aus allen UmsatzDatum erstellen.
			6 ...
		 */

		Validation.combine(Herausgeber.aus(neuerImport.getHerausgeber()), Datei.aus(neuerImport.getDatei()))
			.ap(Import::aus);

		return neuerImport;
	}
}
