package dev.indoors.ringrats.simulation.wrestler;

import dev.indoors.ringrats.core.Simulatable;
import dev.indoors.ringrats.core.engine.RandomNumberGen;
import dev.indoors.ringrats.simulation.action.Action;
import dev.indoors.ringrats.simulation.action.ActionResult;
import dev.indoors.ringrats.simulation.action.move.OffenseMove;
import dev.indoors.ringrats.simulation.attribute.Attribute;
import dev.indoors.ringrats.simulation.attribute.AttributeModifier;
import dev.indoors.ringrats.simulation.condition.Condition;
import dev.indoors.ringrats.simulation.condition.Position;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

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
	Wrestler lookingAt;
	List<OffenseMove> customGrapples = new ArrayList<>();
	List<OffenseMove> customStrikes = new ArrayList<>();

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

	public Action chooseAction(List<Action> actions, Wrestler target) {
		List<Action> wrestlerActions = getActionMap().get(position);
		if (wrestlerActions != null) {
			actions.addAll(wrestlerActions);
		}
		Action action = actions.get(RandomNumberGen.getInstance().randomInteger(actions.size() - 1));
		action.setTarget(target);
		return action;
	}

	public ActionResult performAction(Action action) {
		return action.perform(this);
	}

}

