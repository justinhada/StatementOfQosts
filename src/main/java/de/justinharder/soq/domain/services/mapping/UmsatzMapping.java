package de.justinharder.soq.domain.services.mapping;

import de.justinharder.soq.domain.model.Umsatz;
import de.justinharder.soq.domain.services.dto.GespeicherterUmsatz;
import lombok.NonNull;

import javax.enterprise.context.Dependent;

@Dependent
public class UmsatzMapping implements Mapping<Umsatz, GespeicherterUmsatz>
{
	@Override
	public GespeicherterUmsatz mappe(@NonNull Umsatz umsatz)
	{
		return new GespeicherterUmsatz(
			umsatz.getId().getWert().toString(),
			umsatz.getDatum().toString(),
			umsatz.getBetrag().toString(),
			umsatz.getVerwendungszweck().getWert(),
			umsatz.getBankverbindungAuftraggeber().getIban().getWert(),
			umsatz.getBankverbindungZahlungsbeteiligter().getIban().getWert());
	}
}
