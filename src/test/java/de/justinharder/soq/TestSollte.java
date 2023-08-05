package de.justinharder.soq;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.function.Executable;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class TestSollte
{
	protected void validiereNull(Executable... executables)
	{
		assertAll(Stream.of(executables)
			.map(executable -> (Executable) () -> assertThrows(NullPointerException.class, executable))
			.toList());
	}
}
