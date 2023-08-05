package de.justinharder.soq.view;

import de.justinharder.soq.view.theme.ThemeRessource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Ressource
{
	protected ThemeRessource themeRessource;

	protected Ressource(@NonNull ThemeRessource themeRessource)
	{
		this.themeRessource = themeRessource;
	}
}
