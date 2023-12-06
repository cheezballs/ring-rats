package dev.indoors.ringrats.service.impl;

import dev.indoors.ringrats.service.SimulationService;
import dev.indoors.ringrats.simulation.match.Match;
import dev.indoors.ringrats.simulation.match.MatchConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SimulationServiceImpl implements SimulationService {

    boolean active;
    Match match;

    @Override
    public void simulateMatch(MatchConfiguration matchConfig) {
        setupMatch(matchConfig);

        while (active) {
            match.simulateTurn();
        }

        finalizeMatch();
    }

    private void finalizeMatch() {
    }

    private void setupMatch(MatchConfiguration matchConfig) {
        log.debug("Setting up simulation.");

        match = new Match(matchConfig.getWrestlers(), matchConfig.getStipulations());
        match.start();
        active = true;
    }
}
