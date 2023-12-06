package dev.indoors.ringrats.simulation.stipulation;

import dev.indoors.ringrats.simulation.condition.Condition;
import dev.indoors.ringrats.simulation.condition.StandingInRingCondition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class OneFallStipulation implements Stipulation {

    private String name = "One Fall";

    @Override
    public Set<Condition> getStartingConditions() {
        Set<Condition> conditions = new HashSet<>();
        conditions.add(new StandingInRingCondition());
        return conditions;
    }
}
