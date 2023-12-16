package dev.indoors.ringrats.core.engine;

import dev.indoors.ringrats.simulation.match.Match;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor
public class Engine {

	Match match;
	int turnNumber;

	@Getter
	boolean running;

	public Engine(Match match) {
		this.match = match;
	}

	public void start() throws IOException {
		turnNumber = 1;
		match.initializeForSimulation();
		running = true;
	}

	public void simulateTurn() {
		match.simulateTurn();
		turnNumber++;
	}

}
