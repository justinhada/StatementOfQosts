package de.justinharder.soq.domain.services.imports.erzeugung;

import de.justinharder.soq.domain.model.Bankverbindung;
import de.justinharder.soq.domain.model.attribute.BIC;
import de.justinharder.soq.domain.model.attribute.IBAN;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.repository.BankverbindungRepository;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.function.Function;

@Dependent
public class BankverbindungErzeugung
{
	@NonNull
	private final BankverbindungRepository bankverbindungRepository;

	@NonNull
	private final BankErzeugung bankErzeugung;

	@Inject
	public BankverbindungErzeugung(
		@NonNull BankverbindungRepository bankverbindungRepository,
		@NonNull BankErzeugung bankErzeugung)
	{
		this.bankverbindungRepository = bankverbindungRepository;
		this.bankErzeugung = bankErzeugung;
	}

	public Validation<Meldungen, Bankverbindung> findeAuftraggeber(@NonNull IBAN iban)
	{
		return bankverbindungRepository.finde(iban).toValidation(Meldungen.aus(Meldung.BANKVERBINDUNG_EXISTIERT_NICHT));
	}

	public Validation<Meldungen, Bankverbindung> findeOderErzeuge(@NonNull IBAN iban, @NonNull BIC bic)
	{
		return Validation.combine(
				Validation.valid(iban),
				bankErzeugung.findeOderErzeuge(bic))
			.ap(Bankverbindung::aus)
			.mapError(Meldungen::aus)
			.flatMap(Function.identity())
			.map(bankverbindung -> bankverbindungRepository.finde(bankverbindung.getIban()).getOrElse(bankverbindung));
	}
}
