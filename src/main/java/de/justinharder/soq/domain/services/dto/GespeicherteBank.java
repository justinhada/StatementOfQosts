package de.justinharder.soq.domain.services.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GespeicherteBank extends Dto<GespeicherteBank>
{
	@NonNull
	private final String id;

	@NonNull
	private final String bezeichnung;

	@NonNull
	private final String bic;

	@Override
	protected GespeicherteBank myself()
	{
		return this;
	}
}
