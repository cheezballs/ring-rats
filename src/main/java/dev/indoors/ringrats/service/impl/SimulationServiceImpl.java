package dev.indoors.ringrats.service.impl;

import dev.indoors.ringrats.match.MatchConfiguration;
import dev.indoors.ringrats.service.SimulationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SimulationServiceImpl implements SimulationService {

    boolean active;
    int turnNumber = 1;


    @Override
    public void simulateMatch(MatchConfiguration matchConfig) {
        setupMatch(matchConfig);

        while (active) {
            simulateTurn();
        }

        finalizeMatch();
    }

    private void finalizeMatch() {
    }

    private void simulateTurn() {
        log.debug("Simulating turn {}.", turnNumber);

        turnNumber++;

        if (turnNumber > 20) {
            active = false;
        }
    }

    private void setupMatch(MatchConfiguration matchConfig) {
        log.debug("Setting up simulation.");

        active = true;
    }
}
