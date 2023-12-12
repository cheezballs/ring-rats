package dev.indoors.ringrats.simulation.stipulation;

import dev.indoors.ringrats.simulation.action.Action;
import dev.indoors.ringrats.simulation.condition.Condition;
import dev.indoors.ringrats.simulation.position.Position;

import java.util.HashMap;
import java.util.Set;

public abstract class Stipulation {

	public abstract Set<Condition> getStartingConditions();

	public abstract String getName();

	public abstract HashMap<Position, Set<Action>> getActionMap();

}
