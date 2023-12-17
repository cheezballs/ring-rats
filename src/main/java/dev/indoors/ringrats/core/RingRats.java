package dev.indoors.ringrats.core;

import dev.indoors.ringrats.core.engine.Engine;
import dev.indoors.ringrats.simulation.action.ActionResult;
import dev.indoors.ringrats.simulation.action.Damage;
import dev.indoors.ringrats.simulation.match.Match;
import dev.indoors.ringrats.simulation.match.MatchResult;
import dev.indoors.ringrats.simulation.match.TurnResult;
import dev.indoors.ringrats.simulation.wrestler.Health;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@NoArgsConstructor
public class RingRats {

	public void simulate(Match match) throws IOException {
		Engine engine = new Engine(match);
		engine.start();
		while (engine.isRunning()) {
			engine.simulateTurn();
		}
		MatchResult result = engine.stop();

		int i = 1;
		for (TurnResult turn : result.getTurnResults()) {
			String turnStr = "\n ===   TURN %s   === \n";
			System.out.printf(turnStr, i++);

			for (ActionResult action : turn.getActionResults()) {
				String actionStr = " %s performed %S on %S for %s damage.\n";
				System.out.printf(actionStr, action.getPerformerName(), action.getActionName(), action.getTargetName(), damageStringify(action.getDamageDone()));
			}
		}

		System.out.println("--------------------------------------------");

		for (Wrestler wrestler : match.getWrestlers()) {
			String healthStr = "      %s energy[%s] head[%s] arm[%s] core[%s] leg[%s] back[%s] \n";
			Health health = wrestler.getHealth();
			System.out.println(" starting values: ");
			System.out.printf(healthStr, wrestler.getName(), wrestler.getEnergy().getBeginningValue(), health.getHead().getBeginningValue(), health.getArm().getBeginningValue(),
				health.getCore().getBeginningValue(), health.getLeg().getBeginningValue(), health.getBack().getBeginningValue());
			System.out.println(" ending values: ");
			System.out.printf(healthStr, wrestler.getName(), wrestler.getEnergy().getCurrentValue(), health.getHead().getCurrentValue(), health.getArm().getCurrentValue(),
				health.getCore().getCurrentValue(), health.getLeg().getCurrentValue(), health.getBack().getCurrentValue());
		}
	}

	private Object damageStringify(Damage damageDone) {
		if (damageDone != null) {
			if (damageDone.getHead() != null) {
				return damageDone.getHead() + " head";
			}
			if (damageDone.getCore() != null) {
				return damageDone.getCore() + " core";
			}
			if (damageDone.getLeg() != null) {
				return damageDone.getLeg() + " leg";
			}
			if (damageDone.getBack() != null) {
				return damageDone.getBack() + " back";
			}
			if (damageDone.getArm() != null) {
				return damageDone.getArm() + " arm";
			}
		}
		return " n/a n/a";
	}

}

