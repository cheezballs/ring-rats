package dev.indoors.ringrats.simulation.task;

import dev.indoors.ringrats.simulation.wrestler.Performer;

public class FocusAttentionTask extends Task {

	private static final String NAME = "Focus Attention";

	public FocusAttentionTask(Performer target) {
		super(NAME, target);
	}
}
