package dev.indoors.ringrats.simulation.task;

import dev.indoors.ringrats.simulation.wrestler.Performer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Task {

	private String name;
	private Performer target;

}
