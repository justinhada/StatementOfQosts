package de.justinharder.soq.domain.services.dto;

import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GespeicherterKontoinhaber
{
	@NonNull
	private final String id;

	@NonNull
	private final String kontoinhaber;

	@NonNull
	private final String iban;

	@NonNull
	private final String bank;
}
