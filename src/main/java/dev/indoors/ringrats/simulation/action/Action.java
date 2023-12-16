package dev.indoors.ringrats.simulation.action;

import dev.indoors.ringrats.core.Simulatable;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Action {

	Simulatable target;

	public abstract ActionResult perform(Wrestler performer);

	public abstract String getName();

}
