package dev.indoors.ringrats.simulation.stipulation;

import dev.indoors.ringrats.simulation.action.Action;
import dev.indoors.ringrats.simulation.action.GrappleMove;
import dev.indoors.ringrats.simulation.condition.Condition;
import dev.indoors.ringrats.simulation.condition.StandingCondition;
import dev.indoors.ringrats.simulation.position.InRingPosition;
import dev.indoors.ringrats.simulation.position.Position;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class OneFallStipulation extends Stipulation {

	private String name = "One Fall";
	private Set<Wrestler> activeWrestlers;

	@Override
	public Set<Condition> getStartingConditions() {
		Set<Condition> conditions = new HashSet<>();
		conditions.add(new StandingCondition());
		return conditions;
	}

	@Override
	public HashMap<Position, Set<Action>> getActionMap() {
		return new HashMap<>(Map.of(new InRingPosition(), getInRingActions()));
	}

	private Set<Action> getInRingActions() {
		return Set.of(new GrappleMove());
	}
}
