package de.justinharder.soq.domain.model.attribute;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum Typ
{
	AUSGABE,
	EINNAHME;

	public static Typ aus(Betrag betrag)
	{
		return betrag.istNegativ() ? AUSGABE : EINNAHME;
	}
}
