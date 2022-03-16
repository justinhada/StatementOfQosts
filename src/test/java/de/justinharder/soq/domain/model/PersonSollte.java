package de.justinharder.soq.domain.model;

import de.justinharder.soq.domain.model.attribute.Nachname;
import de.justinharder.soq.domain.model.attribute.Vorname;
import de.justinharder.soq.domain.model.meldung.Ebene;
import de.justinharder.soq.domain.model.meldung.Meldung;
import de.justinharder.soq.domain.model.meldung.Meldungen;
import de.justinharder.soq.domain.model.meldung.Schluessel;
import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Person sollte")
class PersonSollte
{
	private static final Nachname HARDER = new Nachname("Harder");
	private static final Nachname TIEMERDING = new Nachname("Tiemerding");
	private static final Vorname JUSTIN = Vorname.aus("Justin").get();
	private static final Vorname LAURA = Vorname.aus("Laura").get();
	public static final Meldung NACHNAME_MELDUNG =
		new Meldung(Schluessel.NACHNAME, Ebene.FEHLER, "Der Nachname darf nicht leer sein!");
	public static final Meldung VORNAME_MELDUNG =
		new Meldung(Schluessel.VORNAME, Ebene.FEHLER, "Der Vorname darf nicht leer sein!");

	private Person sut;

	private Validation<Meldungen, Person> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Person.aus(null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(NACHNAME_MELDUNG, VORNAME_MELDUNG));

		validierung = Person.aus(null, JUSTIN);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(NACHNAME_MELDUNG));

		validierung = Person.aus(HARDER, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(VORNAME_MELDUNG));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Person.aus(HARDER, JUSTIN);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getNachname()).isEqualTo(HARDER),
			() -> assertThat(sut.getVorname()).isEqualTo(JUSTIN));

		validierung = Person.aus(TIEMERDING, LAURA);
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getNachname()).isEqualTo(TIEMERDING),
			() -> assertThat(sut.getVorname()).isEqualTo(LAURA));
	}
}
