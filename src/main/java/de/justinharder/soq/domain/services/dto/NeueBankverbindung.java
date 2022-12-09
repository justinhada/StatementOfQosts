package de.justinharder.soq.domain.services.dto;

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
public class NeueBankverbindung extends Dto<NeueBankverbindung>
{
	@FormParam("iban")
	private String iban;

	@FormParam("bankId")
	private String bankId;

	@Override
	protected NeueBankverbindung myself()
	{
		return this;
	}
}
