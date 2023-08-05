package de.justinharder.soq.view;

import io.quarkus.qute.TemplateInstance;
import lombok.Getter;

import javax.enterprise.context.SessionScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;

@Getter
@SessionScoped
@Path("/theme")
public class ThemeRessource implements Serializable
{
	private String theme = "dark";

	@POST
	@Consumes
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance aendere()
	{
		theme = theme.equals("dark") ? "light" : "dark";
		return Templates.start(theme);
	}
}
