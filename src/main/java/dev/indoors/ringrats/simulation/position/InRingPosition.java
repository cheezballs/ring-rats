package dev.indoors.ringrats.simulation.position;

import dev.indoors.ringrats.simulation.action.Action;
import dev.indoors.ringrats.simulation.attribute.AttributeModifier;

import java.util.Collection;

public class InRingPosition extends Position {
	@Override
	public Collection<AttributeModifier> getAttributeModifiers() {
		return null;
	}

	@Override
	public Collection<Action> getAvailableActions() {
		return null;
	}
}