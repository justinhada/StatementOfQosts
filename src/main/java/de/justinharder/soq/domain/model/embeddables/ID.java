package de.justinharder.soq.domain.model.embeddables;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class ID implements Serializable
{
	private static final long serialVersionUID = -2765501812381917984L;

	private UUID wert;

	public ID()
	{
		
	}

	public ID(UUID wert)
	{
		this.wert = wert;
	}
}
