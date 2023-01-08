package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BankService;
import de.justinharder.soq.domain.services.dto.GespeicherteBank;
import de.justinharder.soq.domain.services.dto.NeueBank;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/banken")
public class BankenRessource
{
	@NonNull
	private final BankService bankService;

	@NonNull
	private NeueBank neueBank;

	@NonNull
	private GespeicherteBank gespeicherteBank;

	@Inject
	public BankenRessource(
		@NonNull BankService bankService,
		@NonNull NeueBank neueBank,
		@NonNull GespeicherteBank gespeicherteBank)
	{
		this.bankService = bankService;
		this.neueBank = neueBank;
		this.gespeicherteBank = gespeicherteBank;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeFormular()
	{
		return Templates.banken(neueBank, bankService.findeAlle());
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance erstelle(@BeanParam NeueBank neueBank)
	{
		this.neueBank = bankService.erstelle(neueBank);
		return zeigeFormular();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeBank(@PathParam("id") String id)
	{
		return Templates.bank(bankService.finde(id));
	}

	@POST
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance aktualisiere(@BeanParam GespeicherteBank gespeicherteBank)
	{
		this.gespeicherteBank = bankService.aktualisiere(gespeicherteBank);
		return zeigeBank(gespeicherteBank.getId());
	}
}
