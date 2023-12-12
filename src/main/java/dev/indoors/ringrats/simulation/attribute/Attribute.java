package dev.indoors.ringrats.simulation.attribute;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
