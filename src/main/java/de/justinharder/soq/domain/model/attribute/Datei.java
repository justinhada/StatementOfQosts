package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import io.vavr.control.Validation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.nio.charset.StandardCharsets;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Datei extends WertObjekt<byte[]>
{
	@Serial
	private static final long serialVersionUID = 4700784830374128997L;

	private final byte[] wert;

	public static Validation<Meldungen, Datei> aus(byte[] wert)
	{
		return validiere(wert, Meldung.DATEI)
			.flatMap(bytes -> validiereString(new String(bytes), Meldung.DATEI))
			.map(string -> string.getBytes(StandardCharsets.UTF_8))
			.map(Datei::new);
	}

	public boolean istOLB()
	{
		return toString().contains("Inhaberkonto");
	}

	@Override
	public String toString()
	{
		return new String(wert, StandardCharsets.UTF_8);
	}
}
