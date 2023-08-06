package de.justinharder.soq.domain.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.enterprise.context.Dependent;

@Getter
@Setter
@Dependent
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeloeschteBank extends DTO<GeloeschteBank>
{
	@Override
	protected GeloeschteBank myself()
	{
		return this;
	}
}
