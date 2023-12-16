package dev.indoors.ringrats.simulation.match;

import dev.indoors.ringrats.simulation.match.impl.OneVsOne;

public class MatchFactory {

	public static Match getMatch(String name) {
		switch (name) {
			case "OneVsOne":
				return new OneVsOne();
			default:
				throw new IllegalArgumentException("Unknown stipulation: " + name);
		}
	}
}
