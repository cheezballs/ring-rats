package dev.indoors.ringrats.simulation.wrestler;

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
}
