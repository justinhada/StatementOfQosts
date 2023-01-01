package de.justinharder.soq.domain.services.imports.model.csv;

public record Spalte(String wert)
{
	@Override
	public String toString()
	{
		return wert;
	}
}
