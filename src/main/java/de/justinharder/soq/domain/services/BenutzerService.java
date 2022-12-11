package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.attribute.Nachname;
import de.justinharder.soq.domain.model.attribute.Vorname;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.services.dto.GespeicherterBenutzer;
import de.justinharder.soq.domain.services.dto.NeuerBenutzer;
import de.justinharder.soq.domain.services.mapping.BenutzerMapping;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;

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

	@Transactional
	public NeuerBenutzer erstelle(@NonNull NeuerBenutzer neuerBenutzer)
	{
		return Validation.combine(
				Nachname.aus(neuerBenutzer.getNachname()),
				Vorname.aus(neuerBenutzer.getVorname()))
			.ap(Benutzer::aus)
			.mapError(Meldungen::aus)
			.flatMap(Function.identity())
			.fold(neuerBenutzer::fuegeMeldungenHinzu, benutzer -> {
				benutzerRepository.speichere(benutzer);
				return new NeuerBenutzer().fuegeMeldungHinzu(Meldung.BENUTZER_ERSTELLT);
			});
	}
}
