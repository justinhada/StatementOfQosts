package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.UnternehmenService;
import de.justinharder.soq.domain.services.dto.NeuesUnternehmen;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/unternehmen")
public class UnternehmenRessource extends Ressource
{
	@NonNull
	private final UnternehmenService unternehmenService;

	@NonNull
	private NeuesUnternehmen neuesUnternehmen;

	public UnternehmenRessource(
		ThemeRessource themeRessource,
		@NonNull UnternehmenService unternehmenService,
		@NonNull NeuesUnternehmen neuesUnternehmen)
	{
		super(themeRessource);
		this.unternehmenService = unternehmenService;
		this.neuesUnternehmen = neuesUnternehmen;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeErstellungFormular()
	{
		return Templates.unternehmen(
			themeRessource.getTheme(),
			neuesUnternehmen,
			unternehmenService.findeAlle());
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance erstelle(@BeanParam NeuesUnternehmen neuesUnternehmen)
	{
		this.neuesUnternehmen = unternehmenService.erstelle(neuesUnternehmen);
		return zeigeErstellungFormular();
	}
}
