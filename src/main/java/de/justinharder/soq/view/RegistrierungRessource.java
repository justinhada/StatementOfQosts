package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.RegistrierungService;
import de.justinharder.soq.domain.services.dto.NeuerBenutzer;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/registrierung")
public class RegistrierungRessource
{
	@NonNull
	private final RegistrierungService registrierungService;

	@NonNull
	private final Template registrierung;

	@NonNull
	private NeuerBenutzer neuerBenutzer;

	@Inject
	public RegistrierungRessource(
		@NonNull RegistrierungService registrierungService,
		@NonNull Template registrierung,
		@NonNull NeuerBenutzer neuerBenutzer)
	{
		this.registrierungService = registrierungService;
		this.registrierung = registrierung;
		this.neuerBenutzer = neuerBenutzer;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeFormular()
	{
		return registrierung.data("neuerBenutzer", neuerBenutzer);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance erstelleBenutzer(@BeanParam NeuerBenutzer neuerBenutzer)
	{
		this.neuerBenutzer = registrierungService.registriere(neuerBenutzer);
		return zeigeFormular();
	}
}
