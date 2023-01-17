package de.justinharder.soq.domain.services.dto;

import lombok.*;

import javax.enterprise.context.Dependent;
import javax.ws.rs.FormParam;

@Getter
@Setter
@Dependent
@NoArgsConstructor
public class NeuerUmsatz extends DTO<NeuerUmsatz>
{
	private String id;

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

	public NeuerUmsatz(String id)
	{
		this.id = id;
	}

	public NeuerUmsatz(
		String datum,
		String betrag,
		String verwendungszweck,
		String auftraggeberId,
		String zahlungsbeteiligterId)
	{
		this.datum = datum;
		this.betrag = betrag;
		this.verwendungszweck = verwendungszweck;
		this.auftraggeberId = auftraggeberId;
		this.zahlungsbeteiligterId = zahlungsbeteiligterId;
	}

	@Override
	protected NeuerUmsatz myself()
	{
		return this;
	}
}
