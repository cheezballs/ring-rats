package dev.indoors.ringrats.simulation.action;

import dev.indoors.ringrats.simulation.action.move.OffenseMove;
import dev.indoors.ringrats.simulation.attribute.Attribute;
import dev.indoors.ringrats.simulation.wrestler.Health;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrappleAction extends Action {

	OffenseMove move;

	@Override
	public ActionResult perform(Wrestler performer) {

		if (!(target instanceof Wrestler targetWrestler)) {
			throw new UnsupportedOperationException("Target of this action must be of type Wrestler");
		}

		Health targetHealth = targetWrestler.getHealth();
		Attribute head = targetHealth.getHead();
		head.addToCurrentValue(-25);

		return new ActionResult();
	}

	@Override
	public String getName() {
		return move.getName();
	}
}
