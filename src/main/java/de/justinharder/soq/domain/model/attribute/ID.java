package de.justinharder.soq.domain.model.attribute;

import de.justinharder.soq.domain.UuidMapper;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.util.UUID;

@Getter
@Embeddable
@RequiredArgsConstructor
public class ID extends WertObjekt<UUID>
{
	@Serial
	private static final long serialVersionUID = -2765501812381917984L;

	@NonNull
	@Convert(converter = UuidMapper.class)
	@Column(name = "ID", columnDefinition = "VARCHAR(36)")
	private UUID wert;

	public ID()
	{
		this(UUID.randomUUID());
	}
}
