package dev.indoors.ringrats.simulation.attribute;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Attribute {

	private Integer beginningValue;
	private Integer currentValue;

	// for clamping and not breaking the sim
	private Integer maxValue;
	private Integer minValue;

	public void initForSimulation() {
		if (beginningValue != null) {
			currentValue = beginningValue;
		}
	}

}
