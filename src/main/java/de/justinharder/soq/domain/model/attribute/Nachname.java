package de.justinharder.soq.domain.model.attribute;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Nachname extends WertObjekt<String>
{
	@Serial
	private static final long serialVersionUID = -2969529612633353459L;

	@NonNull
	@Column(name = "Nachname")
	private String wert;
}
