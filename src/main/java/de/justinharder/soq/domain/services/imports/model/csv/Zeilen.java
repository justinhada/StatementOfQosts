package de.justinharder.soq.domain.services.imports.model.csv;

import de.justinharder.soq.domain.model.attribute.Datei;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Zeilen extends ArrayList<Zeile>
{
	@Serial
	private static final long serialVersionUID = 7457338124776091513L;

	public Zeilen(List<Zeile> zeilen)
	{
		super(zeilen);
	}

	public static Zeilen aus(Datei datei)
	{
		return new Zeilen(datei.toString().lines()
			.map(Zeile::aus)
			.toList());
	}
}
