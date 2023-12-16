package dev.indoors.ringrats.simulation.match;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MatchPhase {
	@JsonProperty("early")
	EARLY,

	@JsonProperty("middle")
	MIDDLE,

	@JsonProperty("late")
	LATE,

	@JsonProperty("finisher")
	FINISHER

}
