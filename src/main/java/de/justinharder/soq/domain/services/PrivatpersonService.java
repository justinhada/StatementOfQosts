package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.attribute.Nachname;
import de.justinharder.soq.domain.model.attribute.Vorname;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.services.dto.GespeichertePrivatperson;
import de.justinharder.soq.domain.services.dto.NeuePrivatperson;
import de.justinharder.soq.domain.services.mapping.PrivatpersonMapping;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;

import static java.util.function.Predicate.not;

@Dependent
public class PrivatpersonService
{
	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final PrivatpersonMapping privatpersonMapping;

	@Inject
	public PrivatpersonService(
		@NonNull BenutzerRepository benutzerRepository,
		@NonNull PrivatpersonMapping privatpersonMapping)
	{
		this.benutzerRepository = benutzerRepository;
		this.privatpersonMapping = privatpersonMapping;
	}

	public List<GespeichertePrivatperson> findeAlle()
	{
		return benutzerRepository.findeAlle().stream()
			.filter(Benutzer::istPrivatperson)
			.map(privatpersonMapping::mappe)
			.toList();
	}

	@Transactional
	public NeuePrivatperson erstelle(@NonNull NeuePrivatperson neuePrivatperson)
	{
		return Validation.combine(
				Nachname.aus(neuePrivatperson.getNachname()),
				Vorname.aus(neuePrivatperson.getVorname()))
			.ap(Benutzer::aus)
			.mapError(Meldungen::aus)
			.flatMap(Function.identity())
			.filter(not(benutzer -> benutzerRepository.istVorhanden(benutzer.getNachname(), benutzer.getVorname())))
			.getOrElse(Validation.invalid(
				Meldungen.aus(Meldung.NACHNAME_EXISTIERT_BEREITS, Meldung.VORNAME_EXISTIERT_BEREITS)))
			.fold(neuePrivatperson::fuegeMeldungenHinzu, privatperson -> {
				benutzerRepository.speichere(privatperson);
				return new NeuePrivatperson().fuegeMeldungHinzu(Meldung.PRIVATPERSON_ERSTELLT);
			});
	}
}
