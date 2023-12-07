package dev.indoors.ringrats.service.impl;

import dev.indoors.ringrats.service.SimulationService;
import dev.indoors.ringrats.simulation.match.Match;
import dev.indoors.ringrats.simulation.match.MatchConfiguration;
import dev.indoors.ringrats.simulation.match.MatchResults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SimulationServiceImpl implements SimulationService {

	@Override
	public MatchResults simulateMatch(MatchConfiguration matchConfig) {
		log.debug("Setting up simulation.");
		Match match = new Match(matchConfig.getWrestlers(), matchConfig.getStipulations());

		match.start();
		while (match.isMatchActive()) {
			match.simulateTurn();
		}

		return match.end();
	}
}
