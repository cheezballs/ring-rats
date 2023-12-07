package dev.indoors.ringrats.util;

import dev.indoors.ringrats.simulation.match.MatchConfiguration;
import dev.indoors.ringrats.simulation.stipulation.OneFallStipulation;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;

import java.util.Collections;
import java.util.Set;

public final class MockMatchConfigurations {

    private MockMatchConfigurations() {
        throw new UnsupportedOperationException("Utility class not intended for instantiation.");
    }

    public static MatchConfiguration basicOneFall(Set<Wrestler> wrestlers) {
        MatchConfiguration configuration = new MatchConfiguration();
        configuration.setStipulations(Collections.singleton(new OneFallStipulation()));
        configuration.setWrestlers(wrestlers);
        return configuration;
    }
}
