package dev.indoors.ringrats.simulation.match;

import dev.indoors.ringrats.simulation.action.Action;
import dev.indoors.ringrats.simulation.action.GrappleMove;
import dev.indoors.ringrats.simulation.condition.Condition;
import dev.indoors.ringrats.simulation.condition.Position;
import dev.indoors.ringrats.simulation.stipulation.Stipulation;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class OneVsOne extends Match {

	@Override
	Set<Condition> getStartingConditions() {
		return new HashSet<>();
	}

	@Override
	public void simulateTurn() {
		for (Wrestler wrestler : getWrestlersInActionOrder()) {
			List<Action> actions = new ArrayList<>(getActionMap().get(wrestler.getPosition()));
			for (Stipulation stipulation : stipulations) {
				actions.addAll(stipulation.getActionMap().get(wrestler.getPosition()));
			}
			Action action = wrestler.chooseAction(actions);

		}
	}

	@Override
	public String getName() {
		return "OneVsOne";
	}

	@Override
	public void initializeForSimulation() {
		super.initializeForSimulation();
	}

	@Override
	protected Position getInitialWrestlerPosition() {
		return Position.InRing;
	}

	@Override
	public HashMap<Position, List<Action>> getActionMap() {
		HashMap<Position, List<Action>> actions = new HashMap<>(super.getActionMap());
		actions.put(Position.InRing, Collections.singletonList(new GrappleMove()));
		return actions;
	}
}
