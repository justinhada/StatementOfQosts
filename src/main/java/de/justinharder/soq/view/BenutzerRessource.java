package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BenutzerService;
import de.justinharder.soq.domain.services.dto.NeuerBenutzer;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/benutzer")
public class BenutzerRessource
{
	@NonNull
	private final BenutzerService benutzerService;

	@NonNull
	private NeuerBenutzer neuerBenutzer;

	@Inject
	public BenutzerRessource(@NonNull BenutzerService benutzerService, @NonNull NeuerBenutzer neuerBenutzer)
	{
		this.benutzerService = benutzerService;
		this.neuerBenutzer = neuerBenutzer;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeFormular()
	{
		return Templates.benutzer(neuerBenutzer, benutzerService.findeAlle());
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance erstelle(@BeanParam NeuerBenutzer neuerBenutzer)
	{
		this.neuerBenutzer = benutzerService.erstelle(neuerBenutzer);
		return zeigeFormular();
	}
}
