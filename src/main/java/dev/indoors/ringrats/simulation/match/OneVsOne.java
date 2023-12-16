package dev.indoors.ringrats.simulation.match;

import dev.indoors.ringrats.simulation.action.Action;
import dev.indoors.ringrats.simulation.action.ActionResult;
import dev.indoors.ringrats.simulation.action.GrappleAction;
import dev.indoors.ringrats.simulation.action.StrikeAction;
import dev.indoors.ringrats.simulation.condition.Condition;
import dev.indoors.ringrats.simulation.condition.Position;
import dev.indoors.ringrats.simulation.stipulation.Stipulation;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
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
		Collection<Wrestler> wrestlers = getWrestlersInActionOrder();
		for (Wrestler wrestler : wrestlers) {
			List<Action> actions = new ArrayList<>(getActionMap().get(wrestler.getPosition()));
			for (Stipulation stipulation : stipulations) {
				actions.addAll(stipulation.getActionMap().get(wrestler.getPosition()));
			}

			List<Wrestler> otherWrestlers = new ArrayList<>(wrestlers);
			otherWrestlers.remove(wrestler);
			Wrestler target = otherWrestlers.get(0); // get the first since its assumed there's only 1 after filtering

			Action action = wrestler.chooseAction(actions, target);

			if (action instanceof GrappleAction) {

			}

			ActionResult result = wrestler.performAction(action);
		}
	}

	@Override
	public String getName() {
		return "OneVsOne";
	}

	@Override
	public void initializeForSimulation() throws IOException {
		super.initializeForSimulation();
	}

	@Override
	protected Position getInitialWrestlerPosition() {
		return Position.InRing;
	}

	@Override
	public HashMap<Position, List<Action>> getActionMap() {
		HashMap<Position, List<Action>> actions = new HashMap<>(super.getActionMap());

		List<Action> inRingActions = new ArrayList<>();
		inRingActions.add(new GrappleAction());
		inRingActions.add(new StrikeAction());

		actions.put(Position.InRing, inRingActions);
		actions.put(Position.OutOfRing, Collections.singletonList(new GrappleAction()));
		return actions;
	}
}
