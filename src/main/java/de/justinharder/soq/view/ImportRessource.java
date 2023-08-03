package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.dto.NeuerImport;
import de.justinharder.soq.domain.services.imports.ImportService;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/import")
public class ImportRessource extends Ressource
{
	@NonNull
	private final ImportService importService;

	@NonNull
	private NeuerImport neuerImport;

	@Inject
	public ImportRessource(
		ThemeRessource themeRessource,
		@NonNull ImportService importService,
		@NonNull NeuerImport neuerImport)
	{
		super(themeRessource);
		this.importService = importService;
		this.neuerImport = neuerImport;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeImportFormular()
	{
		return Templates.imports(
			themeRessource.getTheme(),
			neuerImport);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public TemplateInstance importiere(@MultipartForm NeuerImport neuerImport)
	{
		// TODO: Nach erfolgreichem Import, weiterleiten auf BuchungenView zur Zuordnung zu einer Kategorie.
		this.neuerImport = importService.importiere(neuerImport);
		return zeigeImportFormular();
	}
}
