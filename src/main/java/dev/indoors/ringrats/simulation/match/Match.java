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

	private static final int TURN_MIDDLE_THRESHOLD = 10;
	private static final int TURN_LATE_THRESHOLD = 20;
	protected Collection<Wrestler> wrestlers;
	protected Collection<Stipulation> stipulations;
	protected BaseMoves baseMoves;
	protected int turnNumber;

	protected abstract Set<Condition> getStartingConditions();

	public abstract TurnResult simulateTurn();

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

		turnNumber = 1;
	}

	protected abstract Position getInitialWrestlerPosition();

	protected MatchPhase getCurrentPhase() {
		if (turnNumber < TURN_MIDDLE_THRESHOLD) {
			return MatchPhase.EARLY;
		} else if (turnNumber < TURN_LATE_THRESHOLD) {
			return MatchPhase.MIDDLE;
		} else {
			return MatchPhase.LATE;
		}
	}

	protected List<Wrestler> getWrestlersInActionOrder() {
		return wrestlers.stream().sorted(new InitiativeComparator()).toList();
	}

}
