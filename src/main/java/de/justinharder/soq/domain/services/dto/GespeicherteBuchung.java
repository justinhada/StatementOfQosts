package de.justinharder.soq.domain.services.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GespeicherteBuchung extends DTO<GespeicherteBuchung>
{
	@NonNull
	private String id;

	@NonNull
	private String kategorie;

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
	protected GespeicherteBuchung myself()
	{
		return this;
	}
}
