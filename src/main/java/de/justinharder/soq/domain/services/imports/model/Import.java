package de.justinharder.soq.domain.services.imports.model;

import de.justinharder.soq.domain.services.imports.model.olb.OlbUmsatzDaten;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Import
{
	private final List<OlbUmsatzDaten> umsatzdaten;
}
