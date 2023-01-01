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
public class NeueKategorie extends DTO<NeueKategorie>
{
	@FormParam("bezeichnung")
	private String bezeichnung;

	@Override
	protected NeueKategorie myself()
	{
		return this;
	}
}
