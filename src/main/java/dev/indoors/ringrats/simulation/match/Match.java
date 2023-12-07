package dev.indoors.ringrats.simulation.match;

import dev.indoors.ringrats.simulation.condition.Condition;
import dev.indoors.ringrats.simulation.stipulation.Stipulation;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Slf4j
public class Match {

    Set<Wrestler> wrestlers;
    Set<Stipulation> stipulations;
    int turnNumber = 0;
    boolean matchActive;

    public Match(Set<Wrestler> wrestlers, Set<Stipulation> stipulations) {
        this.wrestlers = wrestlers;
        this.stipulations = stipulations;
    }

    public void simulateTurn() {
        turnNumber++;
        log.debug("Simulating turn {}.", turnNumber);

        if (turnNumber > 20) {
            matchActive = false;
        }
    }

    public void start() {
        Set<Condition> startingConditions = getStartingConditions();
        for (Wrestler wrestler : wrestlers) {
            startingConditions.addAll(wrestler.getStartingConditions());
            wrestler.setConditions(startingConditions);
        }
        matchActive = true;
    }

    private Set<Condition> getStartingConditions() {
        Set<Condition> startingConditions = new HashSet<>();
        for (Stipulation stipulation : stipulations) {
            startingConditions.addAll(stipulation.getStartingConditions());
        }
        return startingConditions;
    }

    public MatchResults end() {
        return new MatchResults();
    }
}
