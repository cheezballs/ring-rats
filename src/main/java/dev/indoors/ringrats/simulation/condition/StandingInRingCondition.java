package dev.indoors.ringrats.simulation.condition;

import dev.indoors.ringrats.simulation.attribute.AttributeModifier;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class StandingInRingCondition extends Condition {

	private String name = "Standing In Ring";

	public StandingInRingCondition() {
		super(null);
	}

	@Override
	public Collection<AttributeModifier> getAttributeModifiers() {
		return new ArrayList<>();
	}
}
