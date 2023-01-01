package de.justinharder.soq.domain.services.imports.model.csv;

record Spalte(String wert)
{
	@Override
	public String toString()
	{
		return wert;
	}
}
