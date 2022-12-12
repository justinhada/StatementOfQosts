package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.Kategorie;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.repository.KategorieRepository;
import de.justinharder.soq.domain.services.dto.GespeicherteKategorie;
import de.justinharder.soq.domain.services.dto.NeueKategorie;
import de.justinharder.soq.domain.services.mapping.KategorieMapping;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;

import static java.util.function.Predicate.not;

@Dependent
public class KategorieService
{
	@NonNull
	private final KategorieRepository kategorieRepository;

	@NonNull
	private final KategorieMapping kategorieMapping;

	@Inject
	public KategorieService(
		@NonNull KategorieRepository kategorieRepository,
		@NonNull KategorieMapping kategorieMapping)
	{
		this.kategorieRepository = kategorieRepository;
		this.kategorieMapping = kategorieMapping;
	}

	public List<GespeicherteKategorie> findeAlle()
	{
		return kategorieRepository.findeAlle().stream()
			.map(kategorieMapping::mappe)
			.toList();
	}

	@Transactional
	public NeueKategorie erstelle(@NonNull NeueKategorie neueKategorie)
	{
		return Bezeichnung.aus(neueKategorie.getBezeichnung())
			.filter(not(kategorieRepository::istVorhanden))
			.getOrElse(Validation.invalid(Meldungen.aus(Meldung.BEZEICHNUNG_EXISTIERT_BEREITS)))
			.map(Kategorie::aus)
			.flatMap(Function.identity())
			.fold(neueKategorie::fuegeMeldungenHinzu, kategorie -> {
				kategorieRepository.speichere(kategorie);
				return new NeueKategorie().fuegeMeldungHinzu(Meldung.KATEGORIE_ERSTELLT);
			});
	}
}
