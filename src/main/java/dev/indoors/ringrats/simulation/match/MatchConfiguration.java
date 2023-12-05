package dev.indoors.ringrats.simulation.match;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.indoors.ringrats.simulation.stipulation.Stipulation;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;

import java.util.Set;

public class MatchConfiguration {

    @JsonProperty()
    Set<Stipulation> stipulations;

    @JsonProperty()
    Set<Wrestler> wrestlers;

}
