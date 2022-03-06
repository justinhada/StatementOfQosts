package de.justinharder.soq.domain.model.meldung;

import io.vavr.collection.Seq;

import java.io.Serial;
import java.util.ArrayList;

public class Meldungen extends ArrayList<Meldung>
{
	@Serial
	private static final long serialVersionUID = -4698341340398388368L;

	public static Meldungen ausSeq(Seq<Meldung> meldungenSeq)
	{
		var meldungen = new Meldungen();
		meldungenSeq.forEach(meldungen::add);
		return meldungen;
	}
}
