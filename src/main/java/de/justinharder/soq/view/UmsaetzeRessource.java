package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.UmsatzService;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/umsaetze")
public class UmsaetzeRessource
{
	@NonNull
	private final UmsatzService umsatzService;

	@Inject
	public UmsaetzeRessource(@NonNull UmsatzService umsatzService)
	{
		this.umsatzService = umsatzService;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeTabelle()
	{
		return Templates.umsaetze(umsatzService.findeAlle());
	}
}
