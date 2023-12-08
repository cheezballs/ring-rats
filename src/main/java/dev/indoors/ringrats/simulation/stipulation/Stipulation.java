package dev.indoors.ringrats.simulation.stipulation;

import dev.indoors.ringrats.simulation.condition.Condition;

import java.util.Set;

public abstract class Stipulation {

	public abstract Set<Condition> getStartingConditions();

	public abstract String getName();

}
