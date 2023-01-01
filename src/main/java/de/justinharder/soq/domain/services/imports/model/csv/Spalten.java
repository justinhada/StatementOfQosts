package de.justinharder.soq.domain.services.imports.model.csv;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Spalten extends ArrayList<Spalte>
{
	@Serial
	private static final long serialVersionUID = 5118034859061984374L;

	public Spalten(List<Spalte> spalten)
	{
		super(spalten);
	}
}
