package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.repository.UmsatzRepository;
import de.justinharder.soq.domain.services.dto.GespeicherterUmsatz;
import de.justinharder.soq.domain.services.mapping.UmsatzMapping;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class UmsatzService
{
	@NonNull
	private final UmsatzRepository umsatzRepository;

	@NonNull
	private final UmsatzMapping umsatzMapping;

	@Inject
	public UmsatzService(@NonNull UmsatzRepository umsatzRepository, @NonNull UmsatzMapping umsatzMapping)
	{
		this.umsatzRepository = umsatzRepository;
		this.umsatzMapping = umsatzMapping;
	}

	public List<GespeicherterUmsatz> findeAlle()
	{
		return umsatzRepository.findeAlle().stream()
			.map(umsatzMapping::mappe)
			.toList();
	}
}
