package dev.indoors.ringrats.simulation.wrestler;

import dev.indoors.ringrats.simulation.action.Action;
import dev.indoors.ringrats.simulation.condition.Condition;
import dev.indoors.ringrats.simulation.core.Simulatable;
import dev.indoors.ringrats.simulation.position.InRingPosition;
import dev.indoors.ringrats.simulation.position.Position;
import dev.indoors.ringrats.simulation.task.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

@Getter
@Setter
@NoArgsConstructor
public abstract class Performer implements Simulatable {

	String name;

	Health health;
	Stack<Task> tasks = new Stack<>();
	Set<Condition> conditions = new HashSet<>();
	Set<Condition> startingConditions = new HashSet<>();
	Position position;
	HashMap<Position, Set<Action>> uniqueActions;

	public void addTask(Task task) {
		tasks.push(task);
	}

	public void initForSimulation() {
		if (health != null) {
			health.initForSimulation();
		}

		if (position != null) {
			position = new InRingPosition();
		}
	}

}
