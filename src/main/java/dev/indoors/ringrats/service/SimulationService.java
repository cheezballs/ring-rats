package dev.indoors.ringrats.service;

import dev.indoors.ringrats.simulation.match.MatchConfiguration;
import dev.indoors.ringrats.simulation.match.MatchResults;

public interface SimulationService {

    MatchResults simulateMatch(MatchConfiguration matchConfig);
}
