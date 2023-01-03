package de.justinharder.soq.domain.services.imports;

import de.justinharder.soq.domain.model.attribute.Datei;
import de.justinharder.soq.domain.model.attribute.Herausgeber;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.repository.UmsatzRepository;
import de.justinharder.soq.domain.services.dto.NeuerImport;
import de.justinharder.soq.domain.services.imports.erzeugung.UmsatzErzeugung;
import de.justinharder.soq.domain.services.imports.model.Import;
import de.justinharder.soq.domain.services.imports.model.UmsatzDaten;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.function.Function;

@Dependent
public class ImportService
{
	@NonNull
	private final UmsatzRepository umsatzRepository;

	@NonNull
	private final UmsatzErzeugung umsatzErzeugung;

	@Inject
	public ImportService(@NonNull UmsatzRepository umsatzRepository, @NonNull UmsatzErzeugung umsatzErzeugung)
	{
		this.umsatzRepository = umsatzRepository;
		this.umsatzErzeugung = umsatzErzeugung;
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
			6 Umsatz erstellen.
			  a Datum, Betrag, Verwendungszweck erstellen.
			  b Bankverbindungen finden/erstellen.
			    a Bankverbindung finden mit IBAN.
			    b Wenn nicht vorhanden, Bankverbindung erstellen mit IBAN und Bank.
			    c Bank finden mit BIC.
			    d Wenn nicht vorhanden, Bank erstellen mit Bezeichnung und BIC.
			    e Bankverbindung erstellen.
			  c Umsatz aus Attributen erstellen.
		 */

		Validation.combine(Herausgeber.aus(neuerImport.getHerausgeber()), Datei.aus(neuerImport.getDatei()))
			.ap(Import::aus)
			.mapError(Meldungen::aus)
			.flatMap(Function.identity())
			.flatMap(UmsatzDaten::aus);

		return null;
	}
}
