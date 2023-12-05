package dev.indoors.ringrats.wrestler;

import dev.indoors.ringrats.condition.Condition;
import dev.indoors.ringrats.core.Simulatable;
import org.springframework.scheduling.config.Task;

import java.util.Collection;
import java.util.Stack;

public abstract class Performer implements Simulatable {

    int maxEnergy;
    int energy; // current energy
    float staminaCoeff; // rate at which energy depletes

    Health health;
    Stack<Task> tasks;
    Collection<Condition> conditions;
}
