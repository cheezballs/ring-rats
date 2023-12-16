package dev.indoors.ringrats.core.engine;

import dev.indoors.ringrats.simulation.match.Match;
import dev.indoors.ringrats.simulation.match.MatchResult;
import dev.indoors.ringrats.simulation.match.TurnResult;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor
public class Engine {

	Match match;
	MatchResult result = new MatchResult();

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
		return result;
	}

	public void simulateTurn() {
		TurnResult turnResult = match.simulateTurn();
		result.getTurnResults().add(turnResult);
		if (turnResult.isEndMatch()) {
			running = false;
		}
	}

}
