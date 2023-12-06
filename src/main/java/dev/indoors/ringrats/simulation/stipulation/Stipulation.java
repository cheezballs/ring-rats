package dev.indoors.ringrats.simulation.stipulation;

import dev.indoors.ringrats.simulation.condition.Condition;

import java.util.Set;

public interface Stipulation {

    String getName();

    Set<Condition> getStartingConditions();
}
