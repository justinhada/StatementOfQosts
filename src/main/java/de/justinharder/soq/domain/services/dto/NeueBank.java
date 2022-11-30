package de.justinharder.soq.domain.services.dto;

import lombok.*;

import javax.enterprise.context.Dependent;
import javax.ws.rs.FormParam;

@Getter
@Setter
@Dependent
@NoArgsConstructor
@AllArgsConstructor
public class NeueBank extends Dto<NeueBank>
{
	@FormParam("bezeichnung")
	private String bezeichnung;

	@FormParam("bic")
	private String bic;

	@Override
	protected NeueBank myself()
	{
		return this;
	}
}
