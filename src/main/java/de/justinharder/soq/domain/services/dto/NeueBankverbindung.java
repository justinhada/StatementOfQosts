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
public class NeueBankverbindung extends DTO<NeueBankverbindung>
{
	private String id;

	@FormParam("iban")
	private String iban;

	@FormParam("bankId")
	private String bankId;

	public NeueBankverbindung(String id)
	{
		this.id = id;
	}

	public NeueBankverbindung(String iban, String bankId)
	{
		this.iban = iban;
		this.bankId = bankId;
	}

	@Override
	protected NeueBankverbindung myself()
	{
		return this;
	}
}
