package de.justinharder.soq.view;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/start")
public class StartRessource
{
	@NonNull
	private final Template start;

	public StartRessource(@NonNull Template start)
	{
		this.start = start;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeStartseite()
	{
		return start.instance();
	}
}
