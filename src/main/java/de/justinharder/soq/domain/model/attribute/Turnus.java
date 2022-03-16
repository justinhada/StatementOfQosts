package de.justinharder.soq.domain.model.attribute;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum Turnus
{
	MONATLICH(1),
	VIERTELJAEHRLICH(3),
	HALBJAEHRLICH(6),
	JAEHRLICH(12);

	private final int monate;
}
