package dev.indoors.ringrats.simulation.action;

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

	protected Wrestler target;

	public abstract ActionResult perform(Wrestler performer);

	public abstract String getName();

}
