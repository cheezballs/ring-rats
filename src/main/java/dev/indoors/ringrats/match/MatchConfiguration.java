package dev.indoors.ringrats.match;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.indoors.ringrats.stipulation.Stipulation;
import dev.indoors.ringrats.wrestler.Wrestler;

import java.util.Set;

public class MatchConfiguration {

    @JsonProperty()
    Set<Stipulation> stipulations;
    
    @JsonProperty()
    Set<Wrestler> wrestlers;

}
