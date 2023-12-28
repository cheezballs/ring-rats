package dev.indoors.ringrats.simulation.action.impl;

import dev.indoors.ringrats.simulation.action.Action;
import dev.indoors.ringrats.simulation.action.ActionResult;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;

public class StrikeAction extends Action {

	@Override
	public ActionResult perform(Wrestler performer) {

		return new ActionResult();
	}

	@Override
	public String getName() {
		return null;
	}
}
