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
		List<GespeicherterBenutzer> alleBenutzer,
		List<GespeicherteBank> banken);

	public static native TemplateInstance imports();

	public static native TemplateInstance login(
		AngemeldeterBenutzer angemeldeterBenutzer);

	public static native TemplateInstance kontoinhaberWeiterleitung(
		NeuerKontoinhaber neuerKontoinhaber,
		List<GespeicherterBenutzer> alleBenutzer,
		GespeicherteBankverbindung bankverbindung);

	public static native TemplateInstance registrierung(
		NeuerBenutzer neuerBenutzer);

	public static native TemplateInstance start();
}
