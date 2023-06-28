package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BankverbindungService;
import de.justinharder.soq.domain.services.BuchungService;
import de.justinharder.soq.domain.services.KategorieService;
import de.justinharder.soq.domain.services.UmsatzService;
import de.justinharder.soq.domain.services.dto.GespeicherteBuchung;
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
	private final BankverbindungService bankverbindungService;

	@NonNull
	private NeueBuchung neueBuchung;

	@Inject
	public BuchungenRessource(
		@NonNull BuchungService buchungService,
		@NonNull KategorieService kategorieService,
		@NonNull UmsatzService umsatzService,
		@NonNull BankverbindungService bankverbindungService,
		@NonNull NeueBuchung neueBuchung)
	{
		this.buchungService = buchungService;
		this.kategorieService = kategorieService;
		this.umsatzService = umsatzService;
		this.bankverbindungService = bankverbindungService;
		this.neueBuchung = neueBuchung;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeListe()
	{
		// TODO: Zuordnen von Umsätzen, die noch keiner Kategorie zugeordnet sind.
		return Templates.buchungen(neueBuchung, buchungService.findeAlle());
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
		return Templates.umsaetzeWeiterleitung(
			neueBuchung,
			kategorieService.findeAlle(),
			umsatzService.finde(neueBuchung.getUmsatzId()));
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeInformationen(@PathParam("id") String id)
	{
		return Templates.buchung(
			buchungService.finde(id),
			kategorieService.findeAlle(),
			bankverbindungService.findeAlleAuftraggeber(),
			bankverbindungService.findeAlleZahlungsbeteiligten());
	}

	@POST
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance aktualisiere(
		@PathParam("id") String id,
		@BeanParam GespeicherteBuchung gespeicherteBuchung)
	{
		return null;
	}
}
