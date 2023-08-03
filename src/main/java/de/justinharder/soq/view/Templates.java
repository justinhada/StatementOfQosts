package de.justinharder.soq.view;

import de.justinharder.soq.domain.services.dto.*;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@CheckedTemplate
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Templates
{
	public static native TemplateInstance bank(
		String theme,
		GespeicherteBank gespeicherteBank);

	public static native TemplateInstance banken(
		String theme,
		NeueBank neueBank,
		GespeicherteBank gespeicherteBank,
		GeloeschteBank geloeschteBank,
		List<GespeicherteBank> banken);

	public static native TemplateInstance bankverbindungen(
		String theme,
		NeueBankverbindung neueBankverbindung,
		List<GespeicherteBankverbindung> bankverbindungen,
		List<GespeichertePrivatperson> alleBenutzer,
		List<GespeicherteBank> banken);

	public static native TemplateInstance buchung(
		String theme,
		GespeicherteBuchung gespeicherteBuchung,
		List<GespeicherteKategorie> kategorien,
		List<GespeicherterAuftraggeber> alleAuftraggeber,
		List<GespeicherterZahlungsbeteiligter> zahlungsbeteiligte);

	public static native TemplateInstance buchungen(
		String theme,
		NeueBuchung neueBuchung,
		List<GespeicherteBuchung> buchungen);

	public static native TemplateInstance imports(
		String theme,
		NeuerImport neuerImport);

	public static native TemplateInstance kategorien(
		String theme,
		NeueKategorie neueKategorie,
		List<GespeicherteKategorie> kategorien);

	public static native TemplateInstance kontoinhaber(
		String theme,
		NeuerKontoinhaber neuerKontoinhaber,
		List<GespeicherterKontoinhaber> alleKontoinhaber);

	public static native TemplateInstance kontoinhaberWeiterleitung(
		String theme,
		NeuerKontoinhaber neuerKontoinhaber,
		List<GespeichertePrivatperson> alleBenutzer,
		GespeicherteBankverbindung bankverbindung);

	public static native TemplateInstance privatpersonen(
		String theme,
		NeuePrivatperson neuePrivatperson,
		List<GespeichertePrivatperson> privatpersonen);

	public static native TemplateInstance start(
		String theme);

	public static native TemplateInstance umsaetze(
		String theme,
		NeuerUmsatz neuerUmsatz,
		List<GespeicherterAuftraggeber> alleAuftraggeber,
		List<GespeicherterZahlungsbeteiligter> zahlungsbeteiligte,
		List<GespeicherterUmsatz> umsaetze);

	public static native TemplateInstance umsaetzeWeiterleitung(
		String theme,
		NeueBuchung neueBuchung,
		List<GespeicherteKategorie> kategorien,
		GespeicherterUmsatz umsatz);

	public static native TemplateInstance unternehmen(
		String theme,
		NeuesUnternehmen neuesUnternehmen,
		List<GespeichertesUnternehmen> alleUnternehmen);
}
