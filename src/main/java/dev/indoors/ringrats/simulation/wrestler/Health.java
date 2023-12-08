package dev.indoors.ringrats.simulation.wrestler;

import dev.indoors.ringrats.simulation.attribute.Attribute;

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
