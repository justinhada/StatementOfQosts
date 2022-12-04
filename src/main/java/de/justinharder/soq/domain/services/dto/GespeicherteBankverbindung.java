package de.justinharder.soq.domain.services.dto;

import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GespeicherteBankverbindung
{
	@NonNull
	private final String id;

	@NonNull
	private final String iban;

	@NonNull
	private final String nachname;

	@NonNull
	private final String vorname;

	@NonNull
	private final String bank;
}
