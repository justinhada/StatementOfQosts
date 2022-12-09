package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.repository.KontoinhaberRepository;
import de.justinharder.soq.domain.services.dto.NeuerKontoinhaber;
import de.justinharder.soq.domain.services.mapping.KontoinhaberMapping;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.stream.Stream;

@Dependent
public class KontoinhaberService
{
	@NonNull
	private final KontoinhaberRepository kontoinhaberRepository;

	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final KontoinhaberMapping kontoinhaberMapping;

	public KontoinhaberService(
		@NonNull KontoinhaberRepository kontoinhaberRepository,
		@NonNull BenutzerRepository benutzerRepository,
		@NonNull KontoinhaberMapping kontoinhaberMapping)
	{
		this.kontoinhaberRepository = kontoinhaberRepository;
		this.benutzerRepository = benutzerRepository;
		this.kontoinhaberMapping = kontoinhaberMapping;
	}

	public NeuerKontoinhaber erstelle(@NonNull NeuerKontoinhaber neuerKontoinhaber)
	{
		return null;
	}

	private Stream<Validation<Meldungen, Benutzer>> findeBenutzer(List<String> benutzerIds)
	{
		return benutzerIds.stream()
			.map(benutzerId -> ID.aus(benutzerId, Schluessel.BENUTZER)
				.map(benutzerRepository::finde)
				.flatMap(benutzer -> benutzer.toValidation(Meldungen.aus(Meldung.BENUTZER_EXISTIERT_NICHT))));
	}
}
