package de.justinharder.soq.domain.services.imports.model;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class UmsatzDaten
{
	private final String ibanAuftraggeber;
	private final String datum;
	private final String nameZahlungsbeteiligter;
	private final String ibanZahlungsbeteiligter;
	private final String bicZahlungsbeteiligter;
	private final String verwendungszweck;
	private final String betrag;

	protected static UmsatzDaten aus(
		String ibanAuftraggeber,
		String datum,
		String nameZahlungsbeteiligter,
		String ibanZahlungsbeteiligter,
		String bicZahlungsbeteiligter,
		String verwendungszweck,
		String betrag)
	{
		return null;
	}
}
