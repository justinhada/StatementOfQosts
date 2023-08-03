package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.PrivatpersonService;
import de.justinharder.soq.domain.services.dto.NeuePrivatperson;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/privatpersonen")
public class PrivatpersonenRessource extends Ressource
{
	@NonNull
	private final PrivatpersonService privatpersonService;

	@NonNull
	private NeuePrivatperson neuePrivatperson;

	@Inject
	public PrivatpersonenRessource(
		ThemeRessource themeRessource,
		@NonNull PrivatpersonService privatpersonService,
		@NonNull NeuePrivatperson neuePrivatperson)
	{
		super(themeRessource);
		this.privatpersonService = privatpersonService;
		this.neuePrivatperson = neuePrivatperson;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeErstellungFormular()
	{
		return Templates.privatpersonen(neuePrivatperson, privatpersonService.findeAlle());
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance erstelle(@BeanParam NeuePrivatperson neuePrivatperson)
	{
		this.neuePrivatperson = privatpersonService.erstelle(neuePrivatperson);
		return zeigeErstellungFormular();
	}
}
