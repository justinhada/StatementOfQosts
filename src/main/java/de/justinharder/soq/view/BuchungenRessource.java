package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BuchungService;
import de.justinharder.soq.domain.services.KategorieService;
import de.justinharder.soq.domain.services.UmsatzService;
import de.justinharder.soq.domain.services.dto.NeueBuchung;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/buchungen")
public class BuchungenRessource
{
	@NonNull
	private final BuchungService buchungService;

	@NonNull
	private final KategorieService kategorieService;

	@NonNull
	private final UmsatzService umsatzService;

	@NonNull
	private NeueBuchung neueBuchung;

	@Inject
	public BuchungenRessource(
		@NonNull BuchungService buchungService,
		@NonNull KategorieService kategorieService,
		@NonNull UmsatzService umsatzService,
		@NonNull NeueBuchung neueBuchung)
	{
		this.buchungService = buchungService;
		this.kategorieService = kategorieService;
		this.umsatzService = umsatzService;
		this.neueBuchung = neueBuchung;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeListe()
	{
		// TODO: Zuordnen von Umsätzen, die noch keiner Kategorie zugeordnet sind.
		return Templates.buchungen(neueBuchung, buchungService.findeAlle());
	}

	@GET
	@Path("/{umsatzId}")
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeErstellungFormular(@PathParam("umsatzId") String umsatzId)
	{
		return Templates.buchungenWeiterleitung(
			neueBuchung,
			kategorieService.findeAlle(),
			umsatzService.finde(umsatzId));
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance erstelle(@BeanParam NeueBuchung neueBuchung)
	{
		this.neueBuchung = buchungService.erstelle(neueBuchung);
		if (this.neueBuchung.istErfolgreich())
		{
			return zeigeListe();
		}
		return Templates.buchungenWeiterleitung(
			neueBuchung,
			kategorieService.findeAlle(),
			umsatzService.finde(neueBuchung.getUmsatzId()));
	}
}
