package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.LoginService;
import de.justinharder.soq.domain.services.dto.AngemeldeterBenutzer;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@RequestScoped
@Path("/login")
public class LoginRessource
{
	@Context
	private SecurityContext securityContext;

	@NonNull
	private final LoginService loginService;

	@NonNull
	private AngemeldeterBenutzer angemeldeterBenutzer;

	@Inject
	public LoginRessource(@NonNull LoginService loginService, @NonNull AngemeldeterBenutzer angemeldeterBenutzer)
	{
		this.loginService = loginService;
		this.angemeldeterBenutzer = angemeldeterBenutzer;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeFormular()
	{
		var userPrincipal = securityContext.getUserPrincipal();
		if (userPrincipal == null)
		{
			return Templates.login(angemeldeterBenutzer);
		}

		return Templates.start();
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance login(@BeanParam AngemeldeterBenutzer angemeldeterBenutzer)
	{
		this.angemeldeterBenutzer = loginService.login(angemeldeterBenutzer);
		// if (angemeldeterBenutzer.istErfolgreich())
		// {
		// 	return start.instance();
		// }
		return zeigeFormular();
	}
}
