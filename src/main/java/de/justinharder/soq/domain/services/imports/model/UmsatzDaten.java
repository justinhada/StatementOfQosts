package de.justinharder.soq.domain.services.imports.model;

import de.justinharder.soq.domain.model.attribute.Herausgeber;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.services.imports.model.csv.CSV;
import de.justinharder.soq.domain.services.imports.model.csv.Spalten;
import de.justinharder.soq.domain.services.imports.model.csv.Zeile;
import de.justinharder.soq.domain.services.imports.model.csv.Zeilen;
import io.vavr.control.Option;
import io.vavr.control.Validation;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class UmsatzDaten extends ArrayList<UmsatzDatum>
{
	@Serial
	private static final long serialVersionUID = 9132312866401384922L;

	private UmsatzDaten(List<UmsatzDatum> umsatzDaten)
	{
		super(umsatzDaten);
	}

	public static Validation<Meldungen, UmsatzDaten> aus(Import importObjekt)
	{
		return Option.of(importObjekt)
			.toValidation(Meldungen.aus(Meldung.IMPORT))
			.map(Import::getDatei)
			.flatMap(CSV::aus)
			.map(CSV::getZeilen)
			.map(UmsatzDaten::entferneKopfzeile)
			.map(zeilen -> aus(importObjekt.getHerausgeber(), zeilen))
			.map(UmsatzDaten::new);
	}

	private static Zeilen entferneKopfzeile(Zeilen zeilen)
	{
		zeilen.remove(0);
		return zeilen;
	}

	private static List<UmsatzDatum> aus(Herausgeber herausgeber, Zeilen zeilen)
	{
		return zeilen.stream()
			.map(Zeile::getSpalten)
			.map(spalten -> herausgeber.equals(Herausgeber.OLB)
				? UmsatzDatum.aus(erzeugeOLB(spalten))
				: UmsatzDatum.aus(erzeugeVRB(spalten)))
			.toList();
	}

	private static OLBUmsatzDatum erzeugeOLB(Spalten spalten)
	{
		return new OLBUmsatzDatum(
			spalten.get(0).wert(),
			spalten.get(1).wert(),
			spalten.get(2).wert(),
			spalten.get(3).wert(),
			spalten.get(4).wert(),
			spalten.get(5).wert(),
			spalten.get(6).wert(),
			spalten.get(7).wert(),
			spalten.get(8).wert(),
			spalten.get(9).wert(),
			spalten.get(10).wert(),
			spalten.get(11).wert(),
			spalten.get(12).wert(),
			spalten.get(13).wert());
	}

	private static VRBUmsatzDatum erzeugeVRB(Spalten spalten)
	{
		return new VRBUmsatzDatum(
			spalten.get(0).wert(),
			spalten.get(1).wert(),
			spalten.get(2).wert(),
			spalten.get(3).wert(),
			spalten.get(4).wert(),
			spalten.get(5).wert(),
			spalten.get(6).wert(),
			spalten.get(7).wert(),
			spalten.get(8).wert(),
			spalten.get(9).wert(),
			spalten.get(10).wert(),
			spalten.get(11).wert(),
			spalten.get(12).wert(),
			spalten.get(13).wert(),
			spalten.get(14).wert(),
			spalten.get(15).wert(),
			spalten.get(16).wert(),
			spalten.get(17).wert(),
			spalten.get(18).wert());
	}
}
