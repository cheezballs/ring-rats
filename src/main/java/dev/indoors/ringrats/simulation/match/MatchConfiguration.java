package dev.indoors.ringrats.simulation.match;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.indoors.ringrats.simulation.stipulation.Stipulation;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchConfiguration {

	@JsonProperty()
	Set<Stipulation> stipulations;

	@JsonProperty()
	Set<Wrestler> wrestlers;

}
