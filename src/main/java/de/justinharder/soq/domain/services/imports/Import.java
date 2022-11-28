package de.justinharder.soq.domain.services.imports.model;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Import
{
	private final List<OLBImport> umsatzdaten;
}
