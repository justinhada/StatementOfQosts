package de.justinharder.soq.domain.services.dto;

import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GespeicherterBenutzer
{
	@NonNull
	private final String id;

	@NonNull
	private final String nachname;

	@NonNull
	private final String vorname;
}
