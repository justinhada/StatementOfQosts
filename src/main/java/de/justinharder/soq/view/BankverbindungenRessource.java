package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BankService;
import de.justinharder.soq.domain.services.BankverbindungService;
import de.justinharder.soq.domain.services.BenutzerService;
import de.justinharder.soq.domain.services.dto.NeueBankverbindung;
import de.justinharder.soq.domain.services.dto.NeuerKontoinhaber;
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
	private NeueBankverbindung neueBankverbindung;

	@Inject
	public BankverbindungenRessource(
		@NonNull BankverbindungService bankverbindungService,
		@NonNull BenutzerService benutzerService,
		@NonNull BankService bankService,
		@NonNull NeueBankverbindung neueBankverbindung)
	{
		this.bankverbindungService = bankverbindungService;
		this.benutzerService = benutzerService;
		this.bankService = bankService;
		this.neueBankverbindung = neueBankverbindung;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeFormular()
	{
		return Templates.bankverbindungen(
			neueBankverbindung,
			bankverbindungService.findeAlle(),
			benutzerService.findeAlle(),
			bankService.findeAlle());
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance erstelleBankverbindung(@BeanParam NeueBankverbindung neueBankverbindung)
	{
		this.neueBankverbindung = bankverbindungService.erstelle(neueBankverbindung);
		if (this.neueBankverbindung.istErfolgreich())
		{
			return Templates.kontoinhaber(
				new NeuerKontoinhaber(),
				benutzerService.findeAlle(),
				bankverbindungService.finde(this.neueBankverbindung.getId()));
		}
		return zeigeFormular();
	}
}
