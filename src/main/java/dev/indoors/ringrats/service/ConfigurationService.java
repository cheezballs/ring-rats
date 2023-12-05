package dev.indoors.ringrats.service;

import dev.indoors.ringrats.exception.ArgumentException;
import dev.indoors.ringrats.match.MatchConfiguration;

public interface ConfigurationService {

    void readCommandLineArguments(String... args);

    MatchConfiguration buildMatchConfiguration(String... args) throws ArgumentException;

}
