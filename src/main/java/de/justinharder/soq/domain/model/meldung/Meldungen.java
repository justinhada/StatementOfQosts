package de.justinharder.soq.domain.model.meldung;

import io.vavr.collection.Seq;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Meldungen extends ArrayList<Meldung>
{
	@Serial
	private static final long serialVersionUID = -4698341340398388368L;

	public static Meldungen aus(Meldung... meldungArray)
	{
		var meldungen = new Meldungen();
		meldungen.addAll(List.of(meldungArray));
		return meldungen;
	}

	public static Meldungen aus(Seq<Meldungen> meldungenSeq)
	{
		var meldungen = new Meldungen();
		meldungenSeq.forEach(meldungen::addAll);
		return meldungen;
	}
}
