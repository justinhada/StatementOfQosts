package de.justinharder.soq.domain.services;

import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.Umsatz;
import de.justinharder.soq.domain.model.attribute.Betrag;
import de.justinharder.soq.domain.model.attribute.Datum;
import de.justinharder.soq.domain.model.attribute.ID;
import de.justinharder.soq.domain.model.attribute.Verwendungszweck;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import de.justinharder.soq.domain.repository.BankverbindungRepository;
import de.justinharder.soq.domain.repository.UmsatzRepository;
import de.justinharder.soq.domain.services.dto.GespeicherterUmsatz;
import de.justinharder.soq.domain.services.dto.NeuerUmsatz;
import de.justinharder.soq.domain.services.mapping.UmsatzMapping;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;

import static java.util.function.Predicate.not;

@Dependent
public class UmsatzService
{
	@NonNull
	private final UmsatzRepository umsatzRepository;

	@NonNull
	private final BankverbindungRepository bankverbindungRepository;

	@NonNull
	private final UmsatzMapping umsatzMapping;

	@Inject
	public UmsatzService(
		@NonNull UmsatzRepository umsatzRepository,
		@NonNull BankverbindungRepository bankverbindungRepository,
		@NonNull UmsatzMapping umsatzMapping)
	{
		this.umsatzRepository = umsatzRepository;
		this.bankverbindungRepository = bankverbindungRepository;
		this.umsatzMapping = umsatzMapping;
	}

	public List<GespeicherterUmsatz> findeAlle()
	{
		return umsatzRepository.findeAlle().stream()
			.map(umsatzMapping::mappe)
			.toList();
	}

	public GespeicherterUmsatz finde(@NonNull String id)
	{
		return ID.aus(id, Schluessel.UMSATZ)
			.map(umsatzRepository::finde)
			.flatMap(umsatz -> umsatz.toValidation(Meldungen.aus(Meldung.UMSATZ_EXISTIERT_NICHT)))
			.fold(new GespeicherterUmsatz()::fuegeMeldungenHinzu, umsatzMapping::mappe);
	}

	@Transactional
	public NeuerUmsatz erstelle(@NonNull NeuerUmsatz neuerUmsatz)
	{
		return Validation.combine(
				Datum.aus(neuerUmsatz.getDatum()),
				Betrag.aus(neuerUmsatz.getBetrag()),
				Verwendungszweck.aus(neuerUmsatz.getVerwendungszweck()),
				findeBankverbindung(
					neuerUmsatz.getAuftraggeberId(),
					Schluessel.AUFTRAGGEBER,
					Meldung.AUFTRAGGEBER_EXISTIERT_NICHT,
					neuerUmsatz.getZahlungsbeteiligterId(),
					Meldung.AUFTRAGGEBER_GLEICH),
				findeBankverbindung(
					neuerUmsatz.getZahlungsbeteiligterId(),
					Schluessel.ZAHLUNGSBETEILIGTER,
					Meldung.ZAHLUNGSBETEILIGTER_EXISTIERT_NICHT,
					neuerUmsatz.getAuftraggeberId(),
					Meldung.ZAHLUNGSBETEILIGTER_GLEICH))
			.ap(Umsatz::aus)
			.mapError(Meldungen::aus)
			.flatMap(Function.identity())
			.filter(not(umsatz -> umsatzRepository.istVorhanden(
				umsatz.getDatum(),
				umsatz.getBetrag(),
				umsatz.getVerwendungszweck(),
				umsatz.getBankverbindungAuftraggeber(),
				umsatz.getBankverbindungZahlungsbeteiligter())))
			.getOrElse(Validation.invalid(Meldungen.aus(Meldung.UMSATZ_EXISTIERT_BEREITS)))
			.fold(neuerUmsatz::fuegeMeldungenHinzu, umsatz -> {
				umsatzRepository.speichere(umsatz);
				return new NeuerUmsatz(umsatz.getId().getWert().toString()).fuegeMeldungenHinzu(
					Meldungen.aus(Meldung.UMSATZ_ERSTELLT));
			});
	}

	private Validation<Meldungen, Bankverbindung> findeBankverbindung(
		String id,
		Schluessel schluessel,
		Meldung nichtVorhandenMeldung,
		String andereBankverbindungId,
		Meldung gleichheitMeldung)
	{
		return ID.aus(id, schluessel)
			.map(bankverbindungRepository::finde)
			.flatMap(bv -> bv.toValidation(Meldungen.aus(nichtVorhandenMeldung)))
			.filter(not(bankverbindung -> bankverbindung.getId().getWert().toString().equals(andereBankverbindungId)))
			.getOrElse(Validation.invalid(Meldungen.aus(gleichheitMeldung)));
	}
}
