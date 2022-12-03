package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BankService;
import de.justinharder.soq.domain.services.BankverbindungService;
import de.justinharder.soq.domain.services.BenutzerService;
import de.justinharder.soq.domain.services.dto.NeueBankverbindung;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/bankverbindungen")
public class BankverbindungenRessource
{
	@NonNull
	private final BankverbindungService bankverbindungService;

	@NonNull
	private final BenutzerService benutzerService;

	@NonNull
	private final BankService bankService;

	@NonNull
	private final Template bankverbindungen;

	@NonNull
	private NeueBankverbindung neueBankverbindung;

	@Inject
	public BankverbindungenRessource(
		@NonNull BankverbindungService bankverbindungService,
		@NonNull BenutzerService benutzerService,
		@NonNull BankService bankService,
		@NonNull Template bankverbindungen,
		@NonNull NeueBankverbindung neueBankverbindung)
	{
		this.bankverbindungService = bankverbindungService;
		this.benutzerService = benutzerService;
		this.bankService = bankService;
		this.bankverbindungen = bankverbindungen;
		this.neueBankverbindung = neueBankverbindung;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeFormular()
	{
		return bankverbindungen
			.data("neueBankverbindung", neueBankverbindung)
			.data("bankverbindungen", bankverbindungService.findeAlle())
			.data("banken", bankverbindungService.findeAlle())
			.data("alleBenutzer", benutzerService.findeAlle())
			.data("banken", bankService.findeAlle());
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance erstelleBankverbindung(@BeanParam NeueBankverbindung neueBankverbindung)
	{
		this.neueBankverbindung = bankverbindungService.erstelle(neueBankverbindung);
		return zeigeFormular();
	}
}
