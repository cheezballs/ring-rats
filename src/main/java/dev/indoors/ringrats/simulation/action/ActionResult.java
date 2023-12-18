package dev.indoors.ringrats.simulation.action;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActionResult {

	String actionName;
	String performerName;
	String targetName;
	Damage damageDone;
	boolean reversed;
	String reversalActionName;
	String reversalPerformerName;
	String reversalTargetName;

}
