package de.justinharder.soq.domain.model.attribute;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum Art
{
	AUSGABE,
	EINNAHME;

	public static Art aus(Betrag betrag)
	{
		return betrag.istNegativ() ? AUSGABE : EINNAHME;
	}
}
