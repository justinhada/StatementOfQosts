package de.justinharder.soq.domain.services.dto;

import lombok.*;

import javax.enterprise.context.Dependent;
import javax.ws.rs.FormParam;

@Getter
@ToString
@Dependent
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GespeicherteBank extends DTO<GespeicherteBank>
{
	@FormParam("id")
	private String id;

	@FormParam("bezeichnung")
	private String bezeichnung;

	@FormParam("bic")
	private String bic;

	@Override
	protected GespeicherteBank myself()
	{
		return this;
	}
}
