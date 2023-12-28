package dev.indoors.ringrats.simulation.action.impl;

import dev.indoors.ringrats.core.engine.RandomNumberGen;
import dev.indoors.ringrats.simulation.action.Action;
import dev.indoors.ringrats.simulation.action.ActionResult;
import dev.indoors.ringrats.simulation.attribute.Attribute;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PinAction extends Action {

	private static String ACTION_NAME = "Pin";
	private static int BASE_PIN_CHANCE = 30;
	private static float MIN_CHANCE_RATIO = .3f;
	private static float INCREMENTS = .05f;
	private static int INCREMENTS_CHANCE = 10;

	@Override
	public ActionResult perform(Wrestler performer) {
		ActionResult result = new ActionResult();
		result.setPerformerName(performer.getName());
		result.setTargetName(target.getName());
		result.setActionName(getName());

		Attribute mostDamagedAttribute = target.getHealth().getMostDamagedForPin();
		float damageRatio = mostDamagedAttribute.getDamageRatio();

		float pinChanceAdd = ((damageRatio - MIN_CHANCE_RATIO) / INCREMENTS) * INCREMENTS_CHANCE;
		int totalPinChance = BASE_PIN_CHANCE + (int) pinChanceAdd;
		int pinRoll = RandomNumberGen.getInstance().pinRoll();

		if (pinRoll <= totalPinChance) {
			result.setPinCount(3f);
			result.setPinned(true);
		} else {
			result.setPinCount(RandomNumberGen.getInstance().pinSecondsKickout());
			result.setPinned(false);
		}

		return result;
	}

	@Override
	public String getName() {
		return ACTION_NAME;
	}
}
