package dev.indoors.ringrats.simulation.action.move;

import dev.indoors.ringrats.simulation.action.Damage;
import dev.indoors.ringrats.simulation.match.MatchPhase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class OffenseMove {

	String name;
	Damage damage;
	int energy;
	boolean collidable;
	MatchPhase phase;
	List<Reversal> reversals;

}
