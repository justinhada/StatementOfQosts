package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BankService;
import de.justinharder.soq.domain.services.dto.NeueBank;
import io.quarkus.qute.Template;
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
	private final Template banken;

	@NonNull
	private NeueBank neueBank;

	@Inject
	public BankenRessource(@NonNull BankService bankService, @NonNull Template banken, @NonNull NeueBank neueBank)
	{
		this.bankService = bankService;
		this.banken = banken;
		this.neueBank = neueBank;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeFormular()
	{
		return banken
			.data("neueBank", neueBank)
			.data("banken", bankService.findeAlle());
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance erstelleBank(@BeanParam NeueBank neueBank)
	{
		this.neueBank = bankService.erstelle(neueBank);
		return zeigeFormular();
	}
}
