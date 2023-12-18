package dev.indoors.ringrats.simulation.action.impl;

import dev.indoors.ringrats.core.engine.Rand;
import dev.indoors.ringrats.simulation.action.Action;
import dev.indoors.ringrats.simulation.action.ActionResult;
import dev.indoors.ringrats.simulation.action.Damage;
import dev.indoors.ringrats.simulation.action.move.BaseMoves;
import dev.indoors.ringrats.simulation.action.move.OffenseMove;
import dev.indoors.ringrats.simulation.action.move.Reversal;
import dev.indoors.ringrats.simulation.action.move.ReversalMove;
import dev.indoors.ringrats.simulation.attribute.Attribute;
import dev.indoors.ringrats.simulation.wrestler.Health;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrappleAction extends Action {

	private static String REVERSAL_MOVE_NAME = "Reversal";
	private static String REVERSAL_ACTION_NAME = "Reversal";
	private static String REVERSAL_ATTRIBUTE_NAME = "core";
	private static int BASE_REVERSAL_CHANCE = 90;
	private static float CHANCE_PER_SKILL_POINT = .25f;
	private static int MIDLINE_SKILL = 50;
	private static int MAX_SKILL = 100;

	OffenseMove move;

	@Override
	public ActionResult perform(Wrestler performer) {
		if (!(target instanceof Wrestler targetWrestler)) {
			throw new UnsupportedOperationException("Target of this action must be of type Wrestler");
		}
		ActionResult result = new ActionResult();
		result.setPerformerName(performer.getName());
		result.setTargetName(targetWrestler.getName());
		result.setActionName(getName());

		if (grappleIsReversed()) {
			ReversalMove reversalMove = getReversal().getMove();
			OffenseMove reversalOffense = BaseMoves.findFor(reversalMove.getAction(), reversalMove.getName());
			spendEnergy(targetWrestler, reversalOffense != null ? reversalOffense.getEnergy() : 0);
			damageTarget(performer, reversalOffense != null ? reversalOffense.getDamage() : null);

			result.setDamageDone(reversalOffense != null ? reversalOffense.getDamage() : null);
			result.setReversalPerformerName(targetWrestler.getName());
			result.setReversalTargetName(performer.getName());
			result.setReversalActionName(getName());
			result.setReversed(true);
		} else {
			spendEnergy(performer);
			damageTarget(targetWrestler);
			result.setDamageDone(move.getDamage());
		}

		return result;
	}

	private boolean grappleIsReversed() {
		int relevantTargetAttr = findRelevantAttribute((Wrestler) target, getReversal().getAttribute());
		return determineGrappleReversed(relevantTargetAttr);
	}

	private boolean determineGrappleReversed(int relevantTargetAttr) {
		int skill = relevantTargetAttr - MIDLINE_SKILL;
		int chanceBonus = (int) (skill * CHANCE_PER_SKILL_POINT);
		int rand = Rand.between(0, 100);
		return (BASE_REVERSAL_CHANCE + chanceBonus) > rand;
	}

	private int findRelevantAttribute(Wrestler performer, String attribute) {
		return switch (attribute) {
			case "strength" -> performer.getStrength().getCurrentValue();
			case "quickness" -> performer.getQuickness().getCurrentValue();
			default -> MIDLINE_SKILL;
		};
	}

	private Reversal getReversal() {
		if (move.getReversal() != null) {
			return move.getReversal();
		} else {
			Reversal reversal = new Reversal();
			reversal.setAttribute(REVERSAL_ATTRIBUTE_NAME);

			ReversalMove reversalMove = new ReversalMove();
			reversalMove.setAction(REVERSAL_ACTION_NAME);
			reversalMove.setName(REVERSAL_MOVE_NAME);
			reversal.setMove(reversalMove);

			return reversal;
		}
	}

	private void damageTarget(Wrestler targetWrestler) {
		Health targetHealth = targetWrestler.getHealth();
		targetHealth.takeDamage(move.getDamage());
	}

	private void damageTarget(Wrestler targetWrestler, Damage damage) {
		if (damage != null) {
			Health targetHealth = targetWrestler.getHealth();
			targetHealth.takeDamage(damage);
		}
	}

	@Override
	public String getName() {
		return move.getName();
	}

	void spendEnergy(Wrestler wrestler, int amount) {
		Attribute performerEnergy = wrestler.getEnergy();
		performerEnergy.subtract(amount);
	}

	void spendEnergy(Wrestler wrestler) {
		int energyCost = move.getEnergy();
		Attribute performerEnergy = wrestler.getEnergy();
		performerEnergy.subtract(energyCost);
	}
}
