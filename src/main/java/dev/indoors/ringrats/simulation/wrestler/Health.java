package dev.indoors.ringrats.simulation.wrestler;

import dev.indoors.ringrats.simulation.action.Damage;
import dev.indoors.ringrats.simulation.attribute.Attribute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Health {

	Attribute head;
	Attribute arm;
	Attribute leg;
	Attribute back;
	Attribute core;

	public void initForSimulation() {
		head.initForSimulation();
		arm.initForSimulation();
		leg.initForSimulation();
		back.initForSimulation();
		core.initForSimulation();
	}

	public void takeDamage(Damage damage) {
		if (damage.getArm() != null) {
			arm.subtract(damage.getArm());
		}
		if (damage.getBack() != null) {
			back.subtract(damage.getBack());
		}
		if (damage.getCore() != null) {
			core.subtract(damage.getCore());
		}
		if (damage.getLeg() != null) {
			leg.subtract(damage.getLeg());
		}
		if (damage.getHead() != null) {
			head.subtract(damage.getHead());
		}
	}
}
