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
	Integer valueToModifyBy;

	public Integer performModification(Integer originalValue) {
		return switch (modifierType) {
			case MULTIPLY -> performMultiplication(originalValue);
			case ADD -> performAddition(originalValue);
			case SUBTRACT -> performSubtraction(originalValue);
			case DIVIDE -> performDivision(originalValue);
		};
	}

	private Integer performDivision(Integer originalValue) {
		return originalValue / valueToModifyBy;
	}

	private Integer performSubtraction(Integer originalValue) {
		return originalValue - valueToModifyBy;
	}

	private Integer performMultiplication(Integer originalValue) {
		return originalValue * valueToModifyBy;
	}

	private Integer performAddition(Integer originalValue) {
		return originalValue + valueToModifyBy;
	}

}
