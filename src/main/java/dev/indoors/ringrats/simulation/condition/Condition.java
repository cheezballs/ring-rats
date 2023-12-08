package dev.indoors.ringrats.simulation.condition;

import dev.indoors.ringrats.simulation.attribute.AttributeModifier;
import dev.indoors.ringrats.simulation.core.Simulatable;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public abstract class Condition implements Simulatable {

	private Integer turnDuration;
	private Integer turnsLeft;

	public Condition(Integer turnDuration) {
		this.turnDuration = turnDuration;
	}

	public abstract Collection<AttributeModifier> getAttributeModifiers();

}
