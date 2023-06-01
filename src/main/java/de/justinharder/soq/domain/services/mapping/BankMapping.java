package de.justinharder.soq.domain.services.mapping;

import de.justinharder.soq.domain.model.Bank;
import de.justinharder.soq.domain.services.dto.GespeicherteBank;
import lombok.NonNull;

import javax.enterprise.context.Dependent;

@Dependent
public class BankMapping implements Mapping<Bank, GespeicherteBank>
{
	@Override
	public GespeicherteBank mappe(@NonNull Bank bank)
	{
		return new GespeicherteBank(
			bank.getId().getWert().toString(),
			bank.getBezeichnung().getWert(),
			bank.getBic().toString());
	}
}
