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

		spendEnergy(performer);
		damageTarget(targetWrestler);

		ActionResult result = new ActionResult();
		result.damageDone = move.getDamage();
		result.performerName = performer.getName();
		result.targetName = targetWrestler.getName();
		result.actionName = getName();

		return result;
	}

	private void damageTarget(Wrestler targetWrestler) {
		Health targetHealth = targetWrestler.getHealth();
		targetHealth.takeDamage(move.getDamage());
	}

	@Override
	public String getName() {
		return move.getName();
	}

	void spendEnergy(Wrestler wrestler) {
		int energyCost = move.getEnergy();
		Attribute performerEnergy = wrestler.getEnergy();
		performerEnergy.subtract(energyCost);
	}
}
