package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.RegistrierungService;
import de.justinharder.soq.domain.services.dto.NeuePerson;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@RequestScoped
@Path("/registrierung")
public class RegistrierungRessource
{
	@NonNull
	private final RegistrierungService registrierungService;

	@NonNull
	private final Template registrierung;

	@NonNull
	private NeuePerson neuePerson;

	@Inject
	public RegistrierungRessource(
		@NonNull RegistrierungService registrierungService,
		@NonNull Template registrierung,
		@NonNull NeuePerson neuePerson)
	{
		this.registrierungService = registrierungService;
		this.registrierung = registrierung;
		this.neuePerson = neuePerson;
	}

	@GET
	public TemplateInstance zeigeRegistrierungFormular()
	{
		return registrierung.data("neuePerson", neuePerson);
	}

	@POST
	public TemplateInstance erstelleBenutzer(@BeanParam NeuePerson neuePerson)
	{
		this.neuePerson = registrierungService.registriere(neuePerson);
		return zeigeRegistrierungFormular();
	}
}
