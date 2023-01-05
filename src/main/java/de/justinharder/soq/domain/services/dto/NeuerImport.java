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
public class NeuerImport extends DTO<NeuerImport>
{
	@FormParam("herausgeber")
	private String herausgeber;

	@FormParam("datei")
	private byte[] datei;

	@Override
	protected NeuerImport myself()
	{
		return this;
	}
}
