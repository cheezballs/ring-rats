package dev.indoors.ringrats.simulation.match;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.indoors.ringrats.core.Simulatable;
import dev.indoors.ringrats.core.comparator.InitiativeComparator;
import dev.indoors.ringrats.core.deserializer.MatchDeserializer;
import dev.indoors.ringrats.simulation.action.Action;
import dev.indoors.ringrats.simulation.action.move.BaseMoves;
import dev.indoors.ringrats.simulation.condition.Condition;
import dev.indoors.ringrats.simulation.condition.Position;
import dev.indoors.ringrats.simulation.stipulation.Stipulation;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Slf4j
@JsonDeserialize(using = MatchDeserializer.class)
public abstract class Match implements Simulatable {

	Collection<Wrestler> wrestlers;
	Collection<Stipulation> stipulations;
	BaseMoves baseMoves;

	abstract Set<Condition> getStartingConditions();

	public abstract void simulateTurn();

	public abstract String getName();

	@Override
	public HashMap<Position, List<Action>> getActionMap() {
		return new HashMap<>();
	}

	@Override
	public void initializeForSimulation() throws IOException {
		baseMoves = BaseMoves.getInstance();
		Set<Condition> conditions = getStartingConditions();

		for (Stipulation stipulation : stipulations) {
			stipulation.initializeForSimulation();
			conditions.addAll(stipulation.getStartingConditions());
		}

		for (Wrestler wrestler : wrestlers) {
			wrestler.initializeForSimulation();
			wrestler.setConditions(conditions);
			wrestler.setPosition(getInitialWrestlerPosition());
		}
	}

	protected abstract Position getInitialWrestlerPosition();

	Collection<Wrestler> getWrestlersInActionOrder() {
		return wrestlers.stream().sorted(new InitiativeComparator()).toList();
	}

	HashMap<Position, List<Action>> getStipulationsActionMap() {
		HashMap<Position, List<Action>> actionMap = new HashMap<>();
		for (Stipulation stipulation : stipulations) {
			actionMap.putAll(stipulation.getActionMap());
		}
		return actionMap;
	}

}
