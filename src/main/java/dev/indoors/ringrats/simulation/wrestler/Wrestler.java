package dev.indoors.ringrats.simulation.wrestler;

import dev.indoors.ringrats.simulation.attribute.Attribute;
import dev.indoors.ringrats.simulation.attribute.AttributeModifier;
import dev.indoors.ringrats.simulation.condition.Condition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Getter
@Setter
public class Wrestler extends Performer {

	Attribute<Integer> energy;
	Attribute<Float> stamina;
	Attribute<Integer> quickness;

	public int calcInitiative() {
		int currQuickness = quickness.getCurrentValue();
		for (Condition condition : conditions) {
			for (AttributeModifier attribModifier : condition.getAttributeModifiers()) {
				if ("quickness".equalsIgnoreCase(attribModifier.getAttributeName())) {
					currQuickness = attribModifier.performModification(currQuickness).intValue();
				}
			}
		}
		return currQuickness;
	}

}
