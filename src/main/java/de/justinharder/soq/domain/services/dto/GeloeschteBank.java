package de.justinharder.soq.domain.services.dto;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.Dependent;

@Getter
@Setter
@Dependent
public class GeloeschteBank extends DTO<GeloeschteBank>
{
	@Override
	protected GeloeschteBank myself()
	{
		return this;
	}
}
