package de.justinharder.soq.domain.services.imports.erzeugung;

import de.justinharder.soq.domain.model.Bank;
import de.justinharder.soq.domain.model.attribute.BIC;
import de.justinharder.soq.domain.model.attribute.Bezeichnung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.repository.BankRepository;
import io.vavr.control.Validation;
import lombok.NonNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.function.Function;

@Dependent
public class BankErzeugung
{
	@NonNull
	private final BankRepository bankRepository;

	@Inject
	public BankErzeugung(@NonNull BankRepository bankRepository)
	{
		this.bankRepository = bankRepository;
	}

	public Validation<Meldungen, Bank> findeOderErzeuge(@NonNull BIC bic)
	{
		return Validation.combine(
				Bezeichnung.aus(new com.prowidesoftware.swift.model.BIC(bic.getWert()).getInstitution()),
				Validation.valid(bic))
			.ap(Bank::aus)
			.mapError(Meldungen::aus)
			.flatMap(Function.identity())
			.map(bank -> bankRepository.finde(bank.getBic()).getOrElse(bank))
			.peek(bankRepository::speichere);
	}
}
