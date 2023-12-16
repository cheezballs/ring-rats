package dev.indoors.ringrats.core.engine;

import dev.indoors.ringrats.simulation.match.Match;
import dev.indoors.ringrats.simulation.match.MatchResult;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor
public class Engine {

	Match match;

	@Getter
	boolean running;

	public Engine(Match match) {
		this.match = match;
	}

	public void start() throws IOException {
		match.initializeForSimulation();
		running = true;
	}

	public MatchResult stop() {
		MatchResult result = new MatchResult();
		return result;
	}

	public void simulateTurn() {
		match.simulateTurn();
	}

}
