package de.justinharder.soq.domain.model.attribute;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum Art
{
	PRIVATPERSON("Privatperson"),
	UNTERNEHMEN("Unternehmen");

	private final String wert;

	@Override
	public String toString()
	{
		return wert;
	}
}
