package de.justinharder.soq.view.theme;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Theme
{
	LIGHT("light"),
	DARK("dark");

	private final String bezeichnung;

	public boolean isDark()
	{
		return equals(DARK);
	}
}
