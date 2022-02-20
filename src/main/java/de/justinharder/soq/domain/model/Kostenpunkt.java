package de.justinharder.soq.domain.model;

import de.justinharder.soq.domain.model.embeddables.ID;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Kostenpunkt extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -7695291132147617901L;

	@NonNull
	private String posten;

	@NonNull
	private LocalDate datum;

	@NonNull
	private BigDecimal betrag;

	@NonNull
	@ManyToOne
	private Person person;

	public Kostenpunkt(ID id, String posten, LocalDate datum, BigDecimal betrag, Person person)
	{
		super(id);
		this.posten = posten;
		this.datum = datum;
		this.betrag = betrag;
		this.person = person;
	}

	@Override
	public String toString()
	{
		return Objects.toString(this);
	}
}
