package de.justinharder.soq.domain.services.dto;

import lombok.*;

@Getter
@ToString
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
