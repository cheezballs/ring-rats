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
	private static int BASE_REVERSAL_CHANCE = 20;
	private static float CHANCE_PER_SKILL_POINT = .25f;
	private static int MIDLINE_SKILL = 50;
	private static int MAX_SKILL = 100;

	OffenseMove move;

	@Override
	public ActionResult perform(Wrestler performer) {
		ActionResult result = new ActionResult();
		result.setPerformerName(performer.getName());
		result.setTargetName(target.getName());
		result.setActionName(getName());

		if (grappleIsReversed()) {
			ReversalMove reversalMove = getReversal().getMove();
			OffenseMove reversalOffense = BaseMoves.findFor(reversalMove.getAction(), reversalMove.getName());
			spendEnergy(target, reversalOffense != null ? reversalOffense.getEnergy() : 0);
			damageTarget(performer, reversalOffense != null ? reversalOffense.getDamage() : null);

			result.setDamageDone(reversalOffense != null ? reversalOffense.getDamage() : null);
			result.setReversalPerformerName(target.getName());
			result.setReversalTargetName(performer.getName());
			result.setReversalActionName(getName());
			result.setReversed(true);
		} else {
			spendEnergy(performer);
			damageTarget(target);
			result.setDamageDone(move.getDamage());
		}

		return result;
	}

	private boolean grappleIsReversed() {
		int relevantTargetAttr = findRelevantAttribute(target, getReversal().getAttribute());
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
		if (move.getReversals() != null && !move.getReversals().isEmpty()) {
			return move.getReversals().get(Rand.between(0, move.getReversals().size() - 1));
		} else {
			// generic reversal move with no specific move associated
			Reversal reversal = new Reversal();
			reversal.setAttribute(REVERSAL_ATTRIBUTE_NAME);

			ReversalMove reversalMove = new ReversalMove();
			reversalMove.setAction(REVERSAL_ACTION_NAME);
			reversalMove.setName(REVERSAL_MOVE_NAME);
			reversal.setMove(reversalMove);

			return reversal;
		}
	}

	private void damageTarget(Wrestler target) {
		Health targetHealth = target.getHealth();
		targetHealth.takeDamage(move.getDamage());
	}

	private void damageTarget(Wrestler target, Damage damage) {
		if (damage != null) {
			Health targetHealth = target.getHealth();
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
