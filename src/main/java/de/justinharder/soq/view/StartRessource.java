package de.justinharder.soq.view;

import de.justinharder.soq.view.theme.ThemeRessource;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/start")
public class StartRessource extends Ressource
{
	@Inject
	public StartRessource(ThemeRessource themeRessource)
	{
		super(themeRessource);
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeStartseite()
	{
		return Templates.start(themeRessource.getTheme());
	}
}
