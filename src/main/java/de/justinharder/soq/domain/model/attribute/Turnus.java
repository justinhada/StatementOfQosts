package de.justinharder.soq.domain.model.attribute;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum Turnus
{
	MONATLICH(1, "monatlich"),
	QUARTALSWEISE(3, "quartalsweise"),
	HALBJAEHRLICH(6, "halbjährlich"),
	JAEHRLICH(12, "jährlich");

	@Getter
	private final int monate;
	private final String bezeichnung;

	@Override
	public String toString()
	{
		return bezeichnung;
	}
}
