package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.imports.ImportService;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/import")
public class ImportRessource
{
	@NonNull
	private final ImportService importService;

	@Inject
	public ImportRessource(@NonNull ImportService importService)
	{
		this.importService = importService;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeImportFormular()
	{
		return Templates.imports();
	}
}
