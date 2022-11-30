package de.justinharder.soq.domain.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.Dependent;
import javax.ws.rs.FormParam;

@Getter
@Setter
@Dependent
@NoArgsConstructor
@AllArgsConstructor
public class AngemeldeterBenutzer extends Dto<AngemeldeterBenutzer>
{
	@FormParam("benutzername")
	private String benutzername;

	@FormParam("passwort")
	private String passwort;

	@Override
	protected AngemeldeterBenutzer myself()
	{
		return this;
	}
}
