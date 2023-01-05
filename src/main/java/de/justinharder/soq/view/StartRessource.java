package de.justinharder.soq.view;

import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/start")
public class StartRessource
{
	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeStartseite()
	{
		return Templates.start();
	}
}
