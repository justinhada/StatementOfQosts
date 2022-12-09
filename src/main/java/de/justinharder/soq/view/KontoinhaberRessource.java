package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BankverbindungService;
import de.justinharder.soq.domain.services.BenutzerService;
import de.justinharder.soq.domain.services.KontoinhaberService;
import de.justinharder.soq.domain.services.dto.NeuerKontoinhaber;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/bankverbindungen")
public class KontoinhaberRessource
{
	@NonNull
	private final KontoinhaberService kontoinhaberService;

	@NonNull
	private final BenutzerService benutzerService;

	@NonNull
	private final BankverbindungService bankverbindungService;

	private NeuerKontoinhaber neuerKontoinhaber;

	@Inject
	public KontoinhaberRessource(
		@NonNull KontoinhaberService kontoinhaberService,
		@NonNull BenutzerService benutzerService,
		@NonNull BankverbindungService bankverbindungService,
		NeuerKontoinhaber neuerKontoinhaber)
	{
		this.kontoinhaberService = kontoinhaberService;
		this.benutzerService = benutzerService;
		this.bankverbindungService = bankverbindungService;
		this.neuerKontoinhaber = neuerKontoinhaber;
	}

	@GET
	@Path("/{bankverbindungId}")
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeFormular(@PathParam("bankverbindungId") String bankverbindungId)
	{
		return Templates.kontoinhaber(
			neuerKontoinhaber,
			benutzerService.findeAlle(),
			bankverbindungService.finde(bankverbindungId));
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance erstelleKontoinhaber(@BeanParam NeuerKontoinhaber neuerKontoinhaber)
	{
		this.neuerKontoinhaber = kontoinhaberService.erstelle(neuerKontoinhaber);
		return Templates.kontoinhaber(
			neuerKontoinhaber,
			benutzerService.findeAlle(),
			bankverbindungService.finde(neuerKontoinhaber.getBankverbindungId()));
	}
}
