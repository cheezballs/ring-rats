package dev.indoors.ringrats.simulation.attribute;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Attribute<T extends Number> {

	private T beginningValue;
	private T currentValue;

	// for clamping and not breaking the sim
	private T maxValue;
	private T minValue;

}
