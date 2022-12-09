package de.justinharder.soq.domain.services.dto;

import lombok.*;

import javax.enterprise.context.Dependent;

@Getter
@ToString
@Dependent
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GespeicherteBankverbindung extends Dto<GespeicherteBankverbindung>
{
	private String id;

	private String iban;

	private String bank;

	@Override
	protected GespeicherteBankverbindung myself()
	{
		return this;
	}
}
