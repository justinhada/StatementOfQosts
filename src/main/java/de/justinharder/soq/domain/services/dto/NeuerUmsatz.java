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
public class NeuerUmsatz extends DTO<NeuerUmsatz>
{
	@FormParam("datum")
	private String datum;

	@FormParam("betrag")
	private String betrag;

	@FormParam("verwendungszweck")
	private String verwendungszweck;

	@FormParam("auftraggeberId")
	private String auftraggeberId;

	@FormParam("zahlungsbeteiligterId")
	private String zahlungsbeteiligterId;

	@Override
	protected NeuerUmsatz myself()
	{
		return this;
	}
}
