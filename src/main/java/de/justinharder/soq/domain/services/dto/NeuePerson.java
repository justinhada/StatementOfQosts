package de.justinharder.soq.domain.services.dto;

import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import lombok.*;

import javax.enterprise.context.Dependent;
import javax.ws.rs.FormParam;
import java.util.List;

@Dependent
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NeuePerson
{
	@FormParam("emailadresse")
	private String emailadresse;

	@FormParam("benutzername")
	private String benutzername;

	@FormParam("nachname")
	private String nachname;

	@FormParam("vorname")
	private String vorname;

	@FormParam("passwort")
	private String passwort;

	@FormParam("passwortWiederholen")
	private String passwortWiederholen;

	private final Meldungen meldungen = new Meldungen();

	public NeuePerson fuegeMeldungHinzu(@NonNull Meldung meldung)
	{
		meldungen.add(meldung);
		return this;
	}

	public NeuePerson fuegeMeldungenHinzu(@NonNull Meldungen meldungen)
	{
		this.meldungen.addAll(meldungen);
		return this;
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
}
