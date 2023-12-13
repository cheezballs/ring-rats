package dev.indoors.ringrats.simulation.wrestler;

import dev.indoors.ringrats.core.Simulatable;
import dev.indoors.ringrats.core.engine.Rand;
import dev.indoors.ringrats.simulation.action.Action;
import dev.indoors.ringrats.simulation.attribute.Attribute;
import dev.indoors.ringrats.simulation.attribute.AttributeModifier;
import dev.indoors.ringrats.simulation.condition.Condition;
import dev.indoors.ringrats.simulation.condition.Position;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@NoArgsConstructor
@Getter
@Setter
public class Wrestler implements Simulatable {

	String team;
	String name;

	Attribute energy;
	Attribute stamina;
	Attribute quickness;
	Attribute strength;
	Health health;

	Position position;
	Set<Condition> conditions = new HashSet<>();

	public int calcInitiative() {
		int currQuickness = quickness.getCurrentValue();
		for (Condition condition : conditions) {
			for (AttributeModifier attribModifier : condition.getAttributeModifiers()) {
				if ("quickness".equalsIgnoreCase(attribModifier.getAttributeName())) {
					currQuickness = attribModifier.performModification(currQuickness);
				}
			}
		}
		return currQuickness;
	}

	@Override
	public void initializeForSimulation() {
		if (energy != null) {
			energy.initForSimulation();
		}
		if (stamina != null) {
			stamina.initForSimulation();
		}
		if (quickness != null) {
			quickness.initForSimulation();
		}
		if (health != null) {
			health.initForSimulation();
		}
		if (position != null) {
			position = Position.InRing;
		}
		if (strength != null) {
			strength.initForSimulation();
		}
	}

	@Override
	public HashMap<Position, List<Action>> getActionMap() {
		return new HashMap<>();
	}

	public Action chooseAction(List<Action> actions) {
		List<Action> wrestlerActions = getActionMap().get(position);
		if (wrestlerActions != null) {
			actions.addAll(wrestlerActions);
		}
		return actions.get(Rand.between(0, actions.size() - 1));
	}
}
