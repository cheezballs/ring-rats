package dev.indoors.ringrats.service;

import dev.indoors.ringrats.simulation.core.exception.ArgumentException;
import dev.indoors.ringrats.simulation.match.MatchConfiguration;

public interface ConfigurationService {

	void readCommandLineArguments(String... args);

	MatchConfiguration buildMatchConfiguration(String... args) throws ArgumentException;

}
