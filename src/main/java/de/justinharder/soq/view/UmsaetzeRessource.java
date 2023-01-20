package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BankverbindungService;
import de.justinharder.soq.domain.services.KategorieService;
import de.justinharder.soq.domain.services.UmsatzService;
import de.justinharder.soq.domain.services.dto.NeueBuchung;
import de.justinharder.soq.domain.services.dto.NeuerUmsatz;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/umsaetze")
public class UmsaetzeRessource
{
	@NonNull
	private final UmsatzService umsatzService;

	@NonNull
	private final BankverbindungService bankverbindungService;

	@NonNull
	private final KategorieService kategorieService;

	@NonNull
	private NeuerUmsatz neuerUmsatz;

	@Inject
	public UmsaetzeRessource(
		@NonNull UmsatzService umsatzService,
		@NonNull BankverbindungService bankverbindungService,
		@NonNull KategorieService kategorieService,
		@NonNull NeuerUmsatz neuerUmsatz)
	{
		this.umsatzService = umsatzService;
		this.bankverbindungService = bankverbindungService;
		this.kategorieService = kategorieService;
		this.neuerUmsatz = neuerUmsatz;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeErstellungFormular()
	{
		return Templates.umsaetze(
			neuerUmsatz,
			bankverbindungService.findeAlleAuftraggeber(),
			bankverbindungService.findeAlleZahlungsbeteiligten(),
			umsatzService.findeAlle());
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance erstelle(@BeanParam NeuerUmsatz neuerUmsatz)
	{
		this.neuerUmsatz = umsatzService.erstelle(neuerUmsatz);
		if (this.neuerUmsatz.istErfolgreich())
		{
			return Templates.buchungenWeiterleitung(
				new NeueBuchung(),
				kategorieService.findeAlle(),
				umsatzService.finde(this.neuerUmsatz.getId()));
		}
		return zeigeErstellungFormular();
	}
}
