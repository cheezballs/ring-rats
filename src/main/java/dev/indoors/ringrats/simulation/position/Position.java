package dev.indoors.ringrats.simulation.position;

import dev.indoors.ringrats.simulation.attribute.AttributeModifier;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public abstract class Position {

	public abstract Collection<AttributeModifier> getAttributeModifiers();

}
