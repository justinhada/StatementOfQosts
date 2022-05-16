package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.LoginService;
import de.justinharder.soq.domain.services.dto.AngemeldeterBenutzer;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import lombok.NonNull;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/login")
public class LoginRessource
{
	@Context
	private SecurityContext securityContext;

	@NonNull
	private final LoginService loginService;

	@NonNull
	private final Template login;

	@NonNull
	private final Template start;

	@NonNull
	private AngemeldeterBenutzer angemeldeterBenutzer;

	@Inject
	public LoginRessource(
		@NonNull LoginService loginService,
		@NonNull Template login,
		@NonNull Template start,
		@NonNull AngemeldeterBenutzer angemeldeterBenutzer)
	{
		this.loginService = loginService;
		this.login = login;
		this.start = start;
		this.angemeldeterBenutzer = angemeldeterBenutzer;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance zeigeLoginFormular()
	{
		var userPrincipal = securityContext.getUserPrincipal();
		if (userPrincipal == null)
		{
			return login.data("angemeldeterBenutzer", angemeldeterBenutzer);
		}
		System.out.println(userPrincipal.getName());
		return start.instance();
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public TemplateInstance login(@BeanParam AngemeldeterBenutzer angemeldeterBenutzer)
	{
		this.angemeldeterBenutzer = loginService.login(angemeldeterBenutzer);
		if (angemeldeterBenutzer.istErfolgreich())
		{
			return start.instance();
		}
		return zeigeLoginFormular();
	}
}
