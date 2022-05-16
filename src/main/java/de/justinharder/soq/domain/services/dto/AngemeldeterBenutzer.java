package de.justinharder.soq.domain.services.dto;

import de.justinharder.soq.domain.model.meldung.Ebene;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import lombok.*;

import javax.enterprise.context.Dependent;
import javax.ws.rs.FormParam;
import java.util.List;

@Getter
@Setter
@Dependent
@NoArgsConstructor
@AllArgsConstructor
public class AngemeldeterBenutzer
{
	@FormParam("benutzername")
	private String benutzername;

	@FormParam("passwort")
	private String passwort;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private final Meldungen meldungen = new Meldungen();

	public AngemeldeterBenutzer(String benutzername)
	{
		this.benutzername = benutzername;
	}

	public AngemeldeterBenutzer fuegeMeldungHinzu(@NonNull Meldung meldung)
	{
		meldungen.add(meldung);
		return this;
	}

	public AngemeldeterBenutzer fuegeMeldungenHinzu(@NonNull Meldungen meldungen)
	{
		this.meldungen.addAll(meldungen);
		return this;
	}

	public boolean istErfolgreich()
	{
		return meldungen.stream().allMatch(meldung -> meldung.ebene().equals(Ebene.ERFOLG));
	}

	public boolean hatMeldungen()
	{
		return !meldungen.isEmpty();
	}

	public List<Meldung> getMeldungen(Schluessel schluessel)
	{
		return meldungen.stream()
			.filter(meldung -> meldung.schluessel().equals(schluessel))
			.toList();
	}

	public boolean hatMeldungen(Schluessel schluessel)
	{
		return !getMeldungen(schluessel).isEmpty();
	}
}
