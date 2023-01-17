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
public class NeueBuchung extends DTO<NeueBuchung>
{
	@FormParam("umsatzId")
	private String umsatzId;

	@FormParam("kategorieId")
	private String kategorieId;

	@Override
	protected NeueBuchung myself()
	{
		return this;
	}
}
