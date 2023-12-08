package dev.indoors.ringrats.simulation.attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AttributeModifier {

	String attributeName;
	ModifierType modifierType;
	Number valueToModifyBy;

	public Number performModification(Number originalValue) {
		return switch (modifierType) {
			case MULTIPLY -> performMultiplication(originalValue);
			case ADD -> performAddition(originalValue);
			case SUBTRACT -> performSubtraction(originalValue);
			case DIVIDE -> performDivision(originalValue);
		};
	}

	private Number performDivision(Number originalValue) {
		// TODO: logic here and below
		return originalValue;
	}

	private Number performSubtraction(Number originalValue) {
		return originalValue;
	}

	private Number performMultiplication(Number originalValue) {
		return originalValue;
	}

	private Number performAddition(Number originalValue) {
		return originalValue;
	}

}
