package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.BankService;
import de.justinharder.soq.domain.services.BankverbindungService;
import de.justinharder.soq.domain.services.PrivatpersonService;
import de.justinharder.soq.domain.services.dto.NeueBankverbindung;
import de.justinharder.soq.domain.services.dto.NeuerKontoinhaber;
import de.justinharder.soq.view.theme.ThemeRessource;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/bankverbindungen")
public class BankverbindungenRessource extends Ressource
{
	@NonNull
	private final BankverbindungService bankverbindungService;

	@NonNull
	private final PrivatpersonService privatpersonService;

	@NonNull
	private final BankService bankService;

	@NonNull
	private NeueBankverbindung neueBankverbindung;

	@Inject
	public BankverbindungenRessource(
		ThemeRessource themeRessource,
		@NonNull BankverbindungService bankverbindungService,
		@NonNull PrivatpersonService privatpersonService,
		@NonNull BankService bankService,
		@NonNull NeueBankverbindung neueBankverbindung)
	{
		super(themeRessource);
		this.bankverbindungService = bankverbindungService;
		this.privatpersonService = privatpersonService;
		this.bankService = bankService;
		this.neueBankverbindung = neueBankverbindung;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeErstellungFormular()
	{
		return Templates.bankverbindungen(
			themeRessource.getTheme(),
			neueBankverbindung,
			bankverbindungService.findeAlle(),
			privatpersonService.findeAlle(),
			bankService.findeAlle());
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance erstelle(@BeanParam NeueBankverbindung neueBankverbindung)
	{
		this.neueBankverbindung = bankverbindungService.erstelle(neueBankverbindung);
		if (this.neueBankverbindung.istErfolgreich())
		{
			return Templates.kontoinhaberWeiterleitung(
				themeRessource.getTheme(),
				new NeuerKontoinhaber(),
				privatpersonService.findeAlle(),
				bankverbindungService.finde(this.neueBankverbindung.getId()));
		}
		return zeigeErstellungFormular();
	}
}
