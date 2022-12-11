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
public class NeuePrivatperson extends Dto<NeuePrivatperson>
{
	@FormParam("nachname")
	private String nachname;

	@FormParam("vorname")
	private String vorname;

	@Override
	protected NeuePrivatperson myself()
	{
		return this;
	}
}
