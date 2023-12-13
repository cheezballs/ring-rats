package dev.indoors.ringrats.core;

import dev.indoors.ringrats.core.engine.Engine;
import dev.indoors.ringrats.simulation.match.Match;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class RingRats {

	public void simulate(Match match) {
		Engine engine = new Engine(match);
		engine.start();
		while (engine.isRunning()) {
			engine.simulateTurn();
		}
	}

}

