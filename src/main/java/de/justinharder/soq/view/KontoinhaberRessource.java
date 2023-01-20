package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BankverbindungService;
import de.justinharder.soq.domain.services.PrivatpersonService;
import de.justinharder.soq.domain.services.KontoinhaberService;
import de.justinharder.soq.domain.services.dto.NeuerKontoinhaber;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/kontoinhaber")
public class KontoinhaberRessource
{
	@NonNull
	private final KontoinhaberService kontoinhaberService;

	@NonNull
	private final PrivatpersonService privatpersonService;

	@NonNull
	private final BankverbindungService bankverbindungService;

	@NonNull
	private NeuerKontoinhaber neuerKontoinhaber;

	@Inject
	public KontoinhaberRessource(
		@NonNull KontoinhaberService kontoinhaberService,
		@NonNull PrivatpersonService privatpersonService,
		@NonNull BankverbindungService bankverbindungService,
		@NonNull NeuerKontoinhaber neuerKontoinhaber)
	{
		this.kontoinhaberService = kontoinhaberService;
		this.privatpersonService = privatpersonService;
		this.bankverbindungService = bankverbindungService;
		this.neuerKontoinhaber = neuerKontoinhaber;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeListe()
	{
		return Templates.kontoinhaber(
			neuerKontoinhaber,
			kontoinhaberService.findeAlle());
	}

	@GET
	@Path("/{bankverbindungId}")
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeErstellungFormular(@PathParam("bankverbindungId") String bankverbindungId)
	{
		return Templates.kontoinhaberWeiterleitung(
			neuerKontoinhaber,
			privatpersonService.findeAlle(),
			bankverbindungService.finde(bankverbindungId));
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance erstelle(@BeanParam NeuerKontoinhaber neuerKontoinhaber)
	{
		this.neuerKontoinhaber = kontoinhaberService.erstelle(neuerKontoinhaber);
		if (this.neuerKontoinhaber.istErfolgreich())
		{
			return zeigeListe();
		}
		return Templates.kontoinhaberWeiterleitung(
			neuerKontoinhaber,
			privatpersonService.findeAlle(),
			bankverbindungService.finde(neuerKontoinhaber.getBankverbindungId()));
	}
}
