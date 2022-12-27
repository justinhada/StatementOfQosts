package de.justinharder.soq.domain.services.dto;

import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GespeicherterUmsatz
{
	@NonNull
	private final String id;

	@NonNull
	private final String datum;

	@NonNull
	private final String betrag;

	@NonNull
	private final String verwendungszweck;

	@NonNull
	private final String auftraggeber;

	@NonNull
	private final String zahlungsbeteiligter;
}
