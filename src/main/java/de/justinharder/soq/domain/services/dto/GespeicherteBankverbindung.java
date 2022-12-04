package de.justinharder.soq.domain.services.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
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
