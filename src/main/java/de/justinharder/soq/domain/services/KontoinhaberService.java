package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.Kontoinhaber;
import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BankverbindungRepository;
import de.justinharder.soq.domain.repository.BenutzerRepository;
import de.justinharder.soq.domain.repository.KontoinhaberRepository;
import de.justinharder.soq.domain.services.dto.GespeicherterKontoinhaber;
import de.justinharder.soq.domain.services.dto.NeuerKontoinhaber;
import de.justinharder.soq.domain.services.mapping.KontoinhaberMapping;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;

import static java.util.function.Predicate.not;

@Dependent
public class KontoinhaberService
{
	@NonNull
	private final KontoinhaberRepository kontoinhaberRepository;

	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final BankverbindungRepository bankverbindungRepository;

	@NonNull
	private final KontoinhaberMapping kontoinhaberMapping;

	public KontoinhaberService(
		@NonNull KontoinhaberRepository kontoinhaberRepository,
		@NonNull BenutzerRepository benutzerRepository,
		@NonNull BankverbindungRepository bankverbindungRepository,
		@NonNull KontoinhaberMapping kontoinhaberMapping)
	{
		this.kontoinhaberRepository = kontoinhaberRepository;
		this.benutzerRepository = benutzerRepository;
		this.bankverbindungRepository = bankverbindungRepository;
		this.kontoinhaberMapping = kontoinhaberMapping;
	}

	public List<GespeicherterKontoinhaber> findeAlle()
	{
		return kontoinhaberRepository.findeAlle().stream()
			.map(kontoinhaberMapping::mappe)
			.toList();
	}

	@Transactional
	public NeuerKontoinhaber erstelle(@NonNull NeuerKontoinhaber neuerKontoinhaber)
	{
		// TODO: Funktionaler gestalten.
		var bankverbindung = ID.aus(neuerKontoinhaber.getBankverbindungId(), Schluessel.BANKVERBINDUNG)
			.map(bankverbindungRepository::finde)
			.flatMap(bv -> bv.toValidation(Meldungen.aus(Meldung.BANKVERBINDUNG_EXISTIERT_NICHT)));

		// TODO: Warum schlägt der Test sonst fehl?
		if (neuerKontoinhaber.getBenutzerIds().isEmpty())
		{
			return neuerKontoinhaber
				.fuegeMeldungHinzu(Meldung.BENUTZER_MINDESTAUSWAHL)
				.fuegeMeldungenHinzu(bankverbindung.getError());
		}

		return neuerKontoinhaber.getBenutzerIds().stream()
			.map(benutzerId -> ID.aus(benutzerId, Schluessel.BENUTZER)
				.map(benutzerRepository::finde)
				.flatMap(benutzer -> benutzer.toValidation(Meldungen.aus(Meldung.BENUTZER_EXISTIERT_NICHT))))
			.map(benutzer -> Validation.combine(benutzer, bankverbindung)
				.ap(Kontoinhaber::aus)
				.mapError(Meldungen::aus)
				.flatMap(Function.identity())
				.filter(not(kontoinhaber -> kontoinhaberRepository.istVorhanden(
					kontoinhaber.getBenutzer(),
					kontoinhaber.getBankverbindung())))
				.getOrElse(Validation.invalid(Meldungen.aus(Meldung.KONTOINHABER_EXISTIERT_BEREITS)))
				.fold(neuerKontoinhaber::fuegeMeldungenHinzu, kontoinhaber -> {
					kontoinhaberRepository.speichere(kontoinhaber);
					return new NeuerKontoinhaber().fuegeMeldungHinzu(Meldung.KONTOINHABER_ERSTELLT);
				}))
			.reduce(NeuerKontoinhaber::fasseZusammen)
			// TODO: Mehrere gleiche Meldungen weiterhin zusammenfassen und daraufhin im Plural melden.
			.orElseThrow();
	}
}
