package dev.indoors.ringrats.simulation.condition.impl;

import dev.indoors.ringrats.simulation.attribute.AttributeModifier;
import dev.indoors.ringrats.simulation.condition.Condition;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class StandingCondition extends Condition {

	private String name = "Standing";

	public StandingCondition() {
		super(null);
	}

	@Override
	public Collection<AttributeModifier> getAttributeModifiers() {
		return new ArrayList<>();
	}

}
