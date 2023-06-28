package de.justinharder.soq.domain.services.mapping;

import de.justinharder.soq.domain.model.Buchung;
import de.justinharder.soq.domain.services.dto.GespeicherteBuchung;
import lombok.NonNull;

import javax.enterprise.context.Dependent;

@Dependent
public class BuchungMapping implements Mapping<Buchung, GespeicherteBuchung>
{
	@Override
	public GespeicherteBuchung mappe(@NonNull Buchung buchung)
	{
		/*
		TODO: Was wird gebraucht?
		  - ID
		  - Kategorie
		  - Datum
		  - Betrag
		  - Verwendungszweck?
		  - Auftraggeber (IBAN, Kontoinhaber)
		  - Zahlungsbeteiligter (IBAN, Kontoinhaber).
		 */
		return new GespeicherteBuchung(
			buchung.getId().getWert().toString(),
			buchung.getKategorie().getBezeichnung().getWert(),
			buchung.getUmsatz().getDatum().toString(),
			buchung.getUmsatz().getBetrag().toString(),
			buchung.getUmsatz().getVerwendungszweck().getWert(),
			buchung.getUmsatz().getBankverbindungAuftraggeber().getIban().getWert(),
			buchung.getUmsatz().getBankverbindungZahlungsbeteiligter().getIban().getWert());
	}
}
