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

	public void add(Integer value) {
		currentValue += value;
		clamp();
	}

	public void subtract(Integer value) {
		currentValue -= value;
		clamp();
	}

	public float getDamageRatio() {
		return currentValue > 0 ? (float) currentValue / beginningValue : 0;
	}

	public int getDamageTaken() {
		return beginningValue - currentValue;
	}

	private void clamp() {
		if (minValue != null && (currentValue < minValue)) {
			currentValue = minValue;
		}
		if (maxValue != null && (currentValue > maxValue)) {
			currentValue = maxValue;
		}
	}

}
