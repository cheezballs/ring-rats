package dev.indoors.ringrats.simulation.action.impl;

import dev.indoors.ringrats.simulation.action.Action;
import dev.indoors.ringrats.simulation.action.ActionResult;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;

public class StrikeAction extends Action {

	@Override
	public ActionResult perform(Wrestler performer) {
		if (!(target instanceof Wrestler targetWrestler)) {
			throw new UnsupportedOperationException("Target of this action must be of type Wrestler");
		}


		return new ActionResult();
	}

	@Override
	public String getName() {
		return null;
	}
}
