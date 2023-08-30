package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BankService;
import de.justinharder.soq.domain.services.dto.GeloeschteBank;
import de.justinharder.soq.domain.services.dto.GespeicherteBank;
import de.justinharder.soq.domain.services.dto.NeueBank;
import de.justinharder.soq.view.theme.ThemeRessource;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/banken")
public class BankenRessource extends Ressource
{
	@NonNull
	private final BankService bankService;

	@NonNull
	private NeueBank neueBank;

	@NonNull
	private GespeicherteBank gespeicherteBank;

	@NonNull
	private GeloeschteBank geloeschteBank;

	@Inject
	public BankenRessource(
		ThemeRessource themeRessource,
		@NonNull BankService bankService,
		@NonNull NeueBank neueBank,
		@NonNull GespeicherteBank gespeicherteBank,
		@NonNull GeloeschteBank geloeschteBank)
	{
		super(themeRessource);
		this.bankService = bankService;
		this.neueBank = neueBank;
		this.gespeicherteBank = gespeicherteBank;
		this.geloeschteBank = geloeschteBank;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeErstellungFormular()
	{
		return Templates.banken(
			themeRessource.getTheme(),
			neueBank,
			gespeicherteBank,
			geloeschteBank,
			bankService.findeAlle());
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlle()
	{
		return Response
			.ok(bankService.findeAlle())
			.build();
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance erstelle(@BeanParam NeueBank neueBank)
	{
		this.neueBank = bankService.erstelle(neueBank);
		return zeigeErstellungFormular();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeAktualisierungFormular(@PathParam("id") String id)
	{
		return Templates.bank(
			themeRessource.getTheme(),
			bankService.finde(id));
	}

	@POST
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance aktualisiere(@PathParam("id") String id, @BeanParam GespeicherteBank gespeicherteBank)
	{
		this.gespeicherteBank = bankService.aktualisiere(gespeicherteBank);
		if (this.gespeicherteBank.istErfolgreich())
		{
			return zeigeErstellungFormular();
		}
		return Templates.bank(
			themeRessource.getTheme(),
			this.gespeicherteBank);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response loesche(@PathParam("id") String id)
	{
		geloeschteBank = bankService.loesche(id);

		if (!geloeschteBank.istErfolgreich())
		{
			return Response
				.status(Response.Status.OK)
				.entity(geloeschteBank)
				.type(MediaType.APPLICATION_JSON)
				.build();
		}

		return Response
			.ok(geloeschteBank, MediaType.APPLICATION_JSON)
			.build();
	}
}
