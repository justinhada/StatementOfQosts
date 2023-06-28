package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.Buchung;
import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BuchungRepository;
import de.justinharder.soq.domain.repository.KategorieRepository;
import de.justinharder.soq.domain.repository.UmsatzRepository;
import de.justinharder.soq.domain.services.dto.GespeicherteBuchung;
import de.justinharder.soq.domain.services.dto.NeueBuchung;
import de.justinharder.soq.domain.services.mapping.BuchungMapping;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;

import static java.util.function.Predicate.not;

@Dependent
public class BuchungService
{
	@NonNull
	private final BuchungRepository buchungRepository;

	@NonNull
	private final UmsatzRepository umsatzRepository;

	@NonNull
	private final KategorieRepository kategorieRepository;

	@NonNull
	private final BuchungMapping buchungMapping;

	@Inject
	public BuchungService(
		@NonNull BuchungRepository buchungRepository,
		@NonNull UmsatzRepository umsatzRepository,
		@NonNull KategorieRepository kategorieRepository,
		@NonNull BuchungMapping buchungMapping)
	{
		this.buchungRepository = buchungRepository;
		this.umsatzRepository = umsatzRepository;
		this.kategorieRepository = kategorieRepository;
		this.buchungMapping = buchungMapping;
	}

	public List<GespeicherteBuchung> findeAlle()
	{
		return buchungRepository.findeAlle().stream()
			.map(buchungMapping::mappe)
			.toList();
	}

	public GespeicherteBuchung finde(@NonNull String id)
	{
		return ID.aus(id, Schluessel.BUCHUNG)
			.map(buchungRepository::finde)
			.flatMap(buchung -> buchung.toValidation(Meldungen.aus(Meldung.BUCHUNG_EXISTIERT_NICHT)))
			.fold(meldungen -> new GespeicherteBuchung().fuegeMeldungenHinzu(meldungen), buchungMapping::mappe);
	}

	@Transactional
	public NeueBuchung erstelle(@NonNull NeueBuchung neueBuchung)
	{
		return Validation.combine(
				ID.aus(neueBuchung.getUmsatzId(), Schluessel.UMSATZ)
					.map(umsatzRepository::finde)
					.flatMap(u -> u.toValidation(Meldungen.aus(Meldung.UMSATZ_EXISTIERT_NICHT))),
				ID.aus(neueBuchung.getKategorieId(), Schluessel.KATEGORIE)
					.map(kategorieRepository::finde)
					.flatMap(k -> k.toValidation(Meldungen.aus(Meldung.KATEGORIE_EXISTIERT_NICHT))))
			.ap(Buchung::aus)
			.mapError(Meldungen::aus)
			.flatMap(Function.identity())
			.filter(not(buchung -> buchungRepository.istVorhanden(buchung.getUmsatz(), buchung.getKategorie())))
			.getOrElse(Validation.invalid(Meldungen.aus(Meldung.BUCHUNG_EXISTIERT_BEREITS)))
			.fold(neueBuchung::fuegeMeldungenHinzu, buchung -> {
				buchungRepository.speichere(buchung);
				return new NeueBuchung().fuegeMeldungHinzu(Meldung.BUCHUNG_ERSTELLT);
			});
	}
}
