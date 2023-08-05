package de.justinharder.soq.view.theme;

import de.justinharder.soq.view.Templates;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.SessionScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;

@SessionScoped
@Path("/theme")
public class ThemeRessource implements Serializable
{
	private Theme theme = Theme.DARK;

	public String getTheme()
	{
		return theme.getBezeichnung();
	}

	@POST
	@Consumes
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance aendere()
	{
		theme = theme.isDark() ? Theme.LIGHT : Theme.DARK;
		return Templates.start(theme.getBezeichnung());
	}
}
