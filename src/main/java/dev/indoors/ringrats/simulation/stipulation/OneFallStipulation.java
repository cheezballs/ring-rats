package dev.indoors.ringrats.simulation.stipulation;

import dev.indoors.ringrats.simulation.condition.Condition;
import dev.indoors.ringrats.simulation.condition.StandingCondition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class OneFallStipulation extends Stipulation {

	private String name = "One Fall";

	@Override
	public Set<Condition> getStartingConditions() {
		Set<Condition> conditions = new HashSet<>();
		conditions.add(new StandingCondition());
		return conditions;
	}
}
