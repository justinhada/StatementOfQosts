package de.justinharder.soq.domain.services.dto;

import lombok.*;

import javax.enterprise.context.Dependent;

@Getter
@ToString
@Dependent
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GespeicherterUmsatz extends DTO<GespeicherterUmsatz>
{
	@NonNull
	private String id;

	@NonNull
	private String datum;

	@NonNull
	private String betrag;

	@NonNull
	private String verwendungszweck;

	@NonNull
	private String auftraggeber;

	@NonNull
	private String zahlungsbeteiligter;

	@Override
	protected GespeicherterUmsatz myself()
	{
		return this;
	}
}
