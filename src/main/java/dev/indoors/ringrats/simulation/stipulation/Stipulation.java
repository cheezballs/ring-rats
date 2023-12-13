package dev.indoors.ringrats.simulation.stipulation;

import dev.indoors.ringrats.core.Simulatable;
import dev.indoors.ringrats.simulation.condition.Condition;

import java.util.Set;

public abstract class Stipulation implements Simulatable {

	public abstract Set<Condition> getStartingConditions();

}
