package dev.indoors.ringrats.simulation.stipulation;

import dev.indoors.ringrats.simulation.condition.Condition;
import dev.indoors.ringrats.simulation.core.Simulatable;

import java.util.Set;

public abstract class Stipulation implements Simulatable {

	public abstract Set<Condition> getStartingConditions();

}
