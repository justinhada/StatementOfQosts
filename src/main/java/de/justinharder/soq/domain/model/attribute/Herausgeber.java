package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.stream.Stream;

@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum Herausgeber
{
	OLB(1, "Oldenburgische Landesbank AG"),
	VRB(2, "VR BANK Dinklage-Steinfeld eG");

	private final int code;
	private final String wert;

	public static Validation<Meldungen, Herausgeber> aus(String code)
	{
		return Option.ofOptional(Stream.of(values())
				.filter(herausgeber -> herausgeber.code == Integer.parseInt(code))
				.findAny())
			.toValidation(Meldungen.aus(Meldung.HERAUSGEBER_UNGUELTIG));
	}
}
