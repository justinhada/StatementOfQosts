package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BenutzerService;
import de.justinharder.soq.domain.services.dto.NeuePrivatperson;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/privatpersonen")
public class PrivatpersonenRessource
{
	@NonNull
	private final BenutzerService benutzerService;

	@NonNull
	private NeuePrivatperson neuePrivatperson;

	@Inject
	public PrivatpersonenRessource(@NonNull BenutzerService benutzerService, @NonNull NeuePrivatperson neuePrivatperson)
	{
		this.benutzerService = benutzerService;
		this.neuePrivatperson = neuePrivatperson;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeFormular()
	{
		return Templates.privatpersonen(neuePrivatperson, benutzerService.findeAlle());
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance erstelle(@BeanParam NeuePrivatperson neuePrivatperson)
	{
		this.neuePrivatperson = benutzerService.erstelle(neuePrivatperson);
		return zeigeFormular();
	}
}
