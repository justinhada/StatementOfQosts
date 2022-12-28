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
	public static native TemplateInstance banken(
		NeueBank neueBank,
		List<GespeicherteBank> banken);

	public static native TemplateInstance bankverbindungen(
		NeueBankverbindung neueBankverbindung,
		List<GespeicherteBankverbindung> bankverbindungen,
		List<GespeichertePrivatperson> alleBenutzer,
		List<GespeicherteBank> banken);

	public static native TemplateInstance imports(
		NeuerImport neuerImport);

	public static native TemplateInstance kategorien(
		NeueKategorie neueKategorie,
		List<GespeicherteKategorie> kategorien);

	public static native TemplateInstance kontoinhaber(
		NeuerKontoinhaber neuerKontoinhaber,
		List<GespeicherterKontoinhaber> alleKontoinhaber);

	public static native TemplateInstance kontoinhaberWeiterleitung(
		NeuerKontoinhaber neuerKontoinhaber,
		List<GespeichertePrivatperson> alleBenutzer,
		GespeicherteBankverbindung bankverbindung);

	public static native TemplateInstance privatpersonen(
		NeuePrivatperson neuePrivatperson,
		List<GespeichertePrivatperson> privatpersonen);

	public static native TemplateInstance start();

	public static native TemplateInstance umsaetze(
		List<GespeicherterUmsatz> umsaetze);

	public static native TemplateInstance unternehmen(
		NeuesUnternehmen neuesUnternehmen,
		List<GespeichertesUnternehmen> alleUnternehmen);
}
