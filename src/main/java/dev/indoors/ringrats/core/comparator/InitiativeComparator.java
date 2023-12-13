package dev.indoors.ringrats.core.comparator;

import dev.indoors.ringrats.simulation.wrestler.Wrestler;

import java.util.Comparator;

public class InitiativeComparator implements Comparator<Wrestler> {

	@Override
	public int compare(Wrestler w1, Wrestler w2) {
		return -Integer.compare(w1.calcInitiative(), w2.calcInitiative());
	}
}
