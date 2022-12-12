package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.KategorieService;
import de.justinharder.soq.domain.services.dto.NeueKategorie;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/kategorien")
public class KategorienRessource
{
	@NonNull
	private final KategorieService kategorieService;

	@NonNull
	private NeueKategorie neueKategorie;

	@Inject
	public KategorienRessource(@NonNull KategorieService kategorieService, @NonNull NeueKategorie neueKategorie)
	{
		this.kategorieService = kategorieService;
		this.neueKategorie = neueKategorie;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeFormular()
	{
		return Templates.kategorien(neueKategorie, kategorieService.findeAlle());
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance erstelle(@BeanParam NeueKategorie neueKategorie)
	{
		this.neueKategorie = kategorieService.erstelle(neueKategorie);
		return zeigeFormular();
	}
}
