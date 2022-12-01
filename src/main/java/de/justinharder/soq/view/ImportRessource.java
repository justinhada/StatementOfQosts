package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.imports.ImportService;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
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

	@NonNull
	private final Template imports;

	public ImportRessource(@NonNull ImportService importService, @NonNull Template imports)
	{
		this.importService = importService;
		this.imports = imports;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeImportFormular()
	{
		return imports.instance();
	}
}
