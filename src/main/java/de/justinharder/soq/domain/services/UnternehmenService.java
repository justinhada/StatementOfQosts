package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.Benutzer;
import de.justinharder.soq.domain.model.attribute.Firma;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.services.dto.GespeichertesUnternehmen;
import de.justinharder.soq.domain.services.dto.NeuesUnternehmen;
import de.justinharder.soq.domain.services.mapping.UnternehmenMapping;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;

import static java.util.function.Predicate.not;

@Dependent
public class UnternehmenService
{
	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final UnternehmenMapping unternehmenMapping;

	public UnternehmenService(
		@NonNull BenutzerRepository benutzerRepository,
		@NonNull UnternehmenMapping unternehmenMapping)
	{
		this.benutzerRepository = benutzerRepository;
		this.unternehmenMapping = unternehmenMapping;
	}

	public List<GespeichertesUnternehmen> findeAlle()
	{
		return benutzerRepository.findeAlle().stream()
			.filter(Benutzer::istUnternehmen)
			.map(unternehmenMapping::mappe)
			.toList();
	}

	@Transactional
	public NeuesUnternehmen erstelle(NeuesUnternehmen neuesUnternehmen)
	{
		return Firma.aus(neuesUnternehmen.getFirma())
			.filter(not(benutzerRepository::istVorhanden))
			.getOrElse(Validation.invalid(Meldungen.aus(Meldung.FIRMA_EXISTIERT_BEREITS)))
			.map(Benutzer::aus)
			.flatMap(Function.identity())
			.fold(neuesUnternehmen::fuegeMeldungenHinzu, unternehmen -> {
				benutzerRepository.speichere(unternehmen);
				return new NeuesUnternehmen().fuegeMeldungHinzu(Meldung.UNTERNEHMEN_ERSTELLT);
			});
	}
}
