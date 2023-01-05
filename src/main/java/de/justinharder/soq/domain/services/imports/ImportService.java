package de.justinharder.soq.domain.services.imports;

import de.justinharder.soq.domain.model.attribute.Datei;
import de.justinharder.soq.domain.model.attribute.Herausgeber;
import de.justinharder.soq.domain.model.meldung.Meldung;
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
import javax.transaction.Transactional;
import java.util.List;
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

	@Transactional
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
			7 Umsatz speichern.
			  a Banken speichern.
			  b Bankverbindungen speichern.
			  c Umsatz speichern.
		 */

		return Validation.combine(Herausgeber.aus(neuerImport.getHerausgeber()), Datei.aus(neuerImport.getDatei()))
			.ap(Import::aus)
			.mapError(Meldungen::aus)
			.flatMap(Function.identity())
			.flatMap(UmsatzDaten::aus)
			.map(umsatzDaten -> importiere(neuerImport, umsatzDaten))
			.fold(neuerImport::fuegeMeldungenHinzu, neueImports -> neueImports.stream()
				.reduce(NeuerImport::fasseZusammen)
				.orElseThrow());
	}

	private List<NeuerImport> importiere(NeuerImport neuerImport, UmsatzDaten umsatzDaten)
	{
		return umsatzDaten.stream()
			.map(umsatzErzeugung::findeOderErzeuge)
			.map(validierung -> validierung.fold(
				neuerImport::fuegeMeldungenHinzu,
				umsatz -> new NeuerImport().fuegeMeldungHinzu(Meldung.UMSATZ_ERSTELLT)))
			.toList();
	}
}
