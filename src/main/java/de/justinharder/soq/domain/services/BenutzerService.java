package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.services.dto.GespeicherterBenutzer;
import de.justinharder.soq.domain.services.mapping.BenutzerMapping;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class BenutzerService
{
	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final BenutzerMapping benutzerMapping;

	@Inject
	public BenutzerService(@NonNull BenutzerRepository benutzerRepository, @NonNull BenutzerMapping benutzerMapping)
	{
		this.benutzerRepository = benutzerRepository;
		this.benutzerMapping = benutzerMapping;
	}

	public List<GespeicherterBenutzer> findeAlle()
	{
		return benutzerRepository.findeAlle().stream()
			.map(benutzerMapping::mappe)
			.toList();
	}
}
