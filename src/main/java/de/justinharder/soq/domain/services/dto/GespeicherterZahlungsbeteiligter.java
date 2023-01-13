package de.justinharder.soq.domain.services.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GespeicherterZahlungsbeteiligter
{
	private final String id;

	private final String iban;

	private final String bank;

	private final String alleKontoinhaber;

	public boolean hatKontoinhaber()
	{
		return !alleKontoinhaber.isEmpty();
	}
}
