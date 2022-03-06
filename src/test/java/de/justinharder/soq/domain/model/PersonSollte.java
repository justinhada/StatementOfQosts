package de.justinharder.soq.domain.model;

import io.vavr.control.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Person sollte")
class PersonSollte
{
	private Person sut;

	private Validation<Meldungen, Person> validierung;

	@Test
	@DisplayName("invalide sein")
	void test01()
	{
		validierung = Person.aus(null, null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(
				new Meldung("nachname", "fehler", "Der Nachname darf nicht leer sein!"),
				new Meldung("vorname", "fehler", "Der Vorname darf nicht leer sein!")));

		validierung = Person.aus(null, "Justin");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(
				new Meldung("nachname", "fehler", "Der Nachname darf nicht leer sein!")));

		validierung = Person.aus("Harder", null);
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(
				new Meldung("vorname", "fehler", "Der Vorname darf nicht leer sein!")));

		validierung = Person.aus("", "");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(
				new Meldung("nachname", "fehler", "Der Nachname darf nicht leer sein!"),
				new Meldung("vorname", "fehler", "Der Vorname darf nicht leer sein!")));

		validierung = Person.aus("", "Justin");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(
				new Meldung("nachname", "fehler", "Der Nachname darf nicht leer sein!")));

		validierung = Person.aus("Harder", "");
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::get),
			() -> assertThat(validierung.getError()).containsExactlyInAnyOrder(
				new Meldung("vorname", "fehler", "Der Vorname darf nicht leer sein!")));
	}

	@Test
	@DisplayName("valide sein")
	void test02()
	{
		validierung = Person.aus("Harder", "Justin");
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getNachname()).isEqualTo("Harder"),
			() -> assertThat(sut.getVorname()).isEqualTo("Justin"));

		validierung = Person.aus("Tiemerding", "Laura");
		sut = validierung.get();
		assertAll(
			() -> assertThrows(RuntimeException.class, validierung::getError),
			() -> assertThat(sut.getNachname()).isEqualTo("Tiemerding"),
			() -> assertThat(sut.getVorname()).isEqualTo("Laura"));
	}
}
