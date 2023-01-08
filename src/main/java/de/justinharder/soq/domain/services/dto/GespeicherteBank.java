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
	@NonNull
	@FormParam("id")
	private String id;

	@NonNull
	@FormParam("bezeichnung")
	private String bezeichnung;

	@NonNull
	@FormParam("bic")
	private String bic;

	@Override
	protected GespeicherteBank myself()
	{
		return this;
	}
}
