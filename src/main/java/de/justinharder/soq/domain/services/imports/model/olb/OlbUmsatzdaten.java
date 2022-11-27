package de.justinharder.soq.domain.services.imports.model;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Umsatzdaten
{
	// OLB
	private final String inhaberkonto;
	private final String buchungsdatum;
	private final String valuta;
	private final String auftraggeber;
	private final String iban;
	private final String bic;
	private final String verwendungszweck;
	private final String betrag;
	private final String waehrung;
	private final String kundenreferenzen;
	private final String bankreferenz;
	private final String primatnota;
	private final String transaktionsCode;
	private final String transaktionsText;

	// VR BANK
	private final String bezeichnungAuftragskonto;
	private final String ibanAuftragskonto;
	private final String bicAuftragskonto;
	private final String banknameAuftragskonto;
	private final String buchungstag;
	private final String valutadatum;
	private final String nameZahlungsbeteiligter;
	private final String ibanZahlungsbeteiligter;
	private final String bicZahlungsbeteiligter;
	private final String buchungstext;
	private final String verwendungszweck;
	private final String betrag;
	private final String waehrung;
	private final String saldoNachBuchung;
	private final String bemerkung;
	private final String kategorie;
	private final String steuerrelevant;
	private final String glaeubigerId;
	private final String mandatsreferenz;
}
