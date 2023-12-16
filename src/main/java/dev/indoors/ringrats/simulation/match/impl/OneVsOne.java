package dev.indoors.ringrats.simulation.match.impl;

import dev.indoors.ringrats.core.engine.Rand;
import dev.indoors.ringrats.simulation.action.Action;
import dev.indoors.ringrats.simulation.action.ActionResult;
import dev.indoors.ringrats.simulation.action.impl.GrappleAction;
import dev.indoors.ringrats.simulation.action.impl.StrikeAction;
import dev.indoors.ringrats.simulation.action.move.OffenseMove;
import dev.indoors.ringrats.simulation.condition.Condition;
import dev.indoors.ringrats.simulation.condition.Position;
import dev.indoors.ringrats.simulation.match.Match;
import dev.indoors.ringrats.simulation.match.MatchPhase;
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
	protected Set<Condition> getStartingConditions() {
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

			if (action instanceof GrappleAction grappleAction) {
				List<OffenseMove> eligibleMoves = new ArrayList<>();
				eligibleMoves.addAll(getEligibleGrapples(baseMoves.getBasicGrapples(), getCurrentPhase()));
				eligibleMoves.addAll(getEligibleGrapples(wrestler.getCustomGrapples(), getCurrentPhase()));
				OffenseMove move = eligibleMoves.get(Rand.between(0, eligibleMoves.size() - 1));

				grappleAction.setMove(move);
			}

			ActionResult result = wrestler.performAction(action);
		}

		super.simulateTurn();
	}

	private List<OffenseMove> getEligibleGrapples(List<OffenseMove> grapples, MatchPhase phase) {
		return grapples.stream().filter(grapple -> grapple.getPhase().equals(phase)).toList();
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
