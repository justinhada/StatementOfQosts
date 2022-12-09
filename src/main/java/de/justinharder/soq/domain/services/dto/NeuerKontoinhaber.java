package de.justinharder.soq.domain.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.Dependent;
import javax.ws.rs.FormParam;
import java.util.List;

@Getter
@Setter
@Dependent
@NoArgsConstructor
@AllArgsConstructor
public class NeuerKontoinhaber extends Dto<NeuerKontoinhaber>
{
	@FormParam("benutzerIds")
	private List<String> benutzerIds;

	@FormParam("bankverbindungId")
	private String bankverbindungId;

	@Override
	protected NeuerKontoinhaber myself()
	{
		return this;
	}
}
