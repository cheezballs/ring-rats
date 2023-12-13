package dev.indoors.ringrats.core;

import dev.indoors.ringrats.simulation.action.Action;
import dev.indoors.ringrats.simulation.condition.Position;

import java.util.HashMap;
import java.util.List;

public interface Simulatable {

	void initializeForSimulation();

	HashMap<Position, List<Action>> getActionMap();

}
