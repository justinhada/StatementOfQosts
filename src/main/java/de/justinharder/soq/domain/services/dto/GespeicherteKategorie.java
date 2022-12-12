package de.justinharder.soq.domain.services.dto;

import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GespeicherteKategorie
{
	@NonNull
	private final String id;

	@NonNull
	private final String bezeichnung;
}
