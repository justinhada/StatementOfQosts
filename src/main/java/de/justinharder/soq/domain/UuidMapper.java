package de.justinharder.soq.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.UUID;

@Converter(autoApply = true)
public class UuidMapper implements AttributeConverter<UUID, String>
{
	@Override
	public String convertToDatabaseColumn(UUID uuid)
	{
		return uuid == null ? null : uuid.toString();
	}

	@Override
	public UUID convertToEntityAttribute(String uuid)
	{
		return uuid == null ? null : UUID.fromString(uuid);
	}
}
