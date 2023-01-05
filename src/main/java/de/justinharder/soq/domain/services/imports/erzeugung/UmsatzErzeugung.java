package de.justinharder.soq.domain.services.imports.erzeugung;

import de.justinharder.soq.domain.model.Umsatz;
import de.justinharder.soq.domain.model.attribute.*;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.repository.UmsatzRepository;
import de.justinharder.soq.domain.services.imports.model.UmsatzDatum;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Dependent
public class UmsatzErzeugung
{
	@NonNull
	private final UmsatzRepository umsatzRepository;

	@NonNull
	private final BankverbindungErzeugung bankverbindungErzeugung;

	@Inject
	public UmsatzErzeugung(
		@NonNull UmsatzRepository umsatzRepository,
		@NonNull BankverbindungErzeugung bankverbindungErzeugung)
	{
		this.umsatzRepository = umsatzRepository;
		this.bankverbindungErzeugung = bankverbindungErzeugung;
	}

	public Validation<Meldungen, Umsatz> findeOderErzeuge(UmsatzDatum umsatzDatum)
	{
		return Validation.combine(
				Datum.aus(LocalDate.parse(umsatzDatum.datum(), DateTimeFormatter.ofPattern("dd.MM.yyyy"))),
				Betrag.aus(new BigDecimal(umsatzDatum.betrag()
					.replace(".", "")
					.replace(",", "."))),
				Verwendungszweck.aus(umsatzDatum.verwendungszweck()),
				IBAN.aus(umsatzDatum.auftraggeberIBAN())
					.map(bankverbindungErzeugung::findeAuftraggeber)
					.flatMap(Function.identity()),
				Validation.combine(
						IBAN.aus(umsatzDatum.zahlungsbeteiligterIBAN()),
						BIC.aus(umsatzDatum.zahlungsbeteiligterBIC()))
					.ap(bankverbindungErzeugung::findeOderErzeuge)
					.mapError(Meldungen::aus)
					.flatMap(Function.identity()))
			.ap(Umsatz::aus)
			.mapError(Meldungen::aus)
			.flatMap(Function.identity())
			.map(umsatz -> umsatzRepository.finde(
					umsatz.getDatum(),
					umsatz.getBetrag(),
					umsatz.getVerwendungszweck(),
					umsatz.getBankverbindungAuftraggeber(),
					umsatz.getBankverbindungZahlungsbeteiligter())
				.getOrElse(umsatz))
			.peek(umsatzRepository::speichere);
	}
}
