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
public class NeuerImport extends Dto<NeuerImport>
{
	@FormParam("von")
	private String von;

	@FormParam("datei")
	private byte[] datei;

	@Override
	protected NeuerImport myself()
	{
		return this;
	}
}
