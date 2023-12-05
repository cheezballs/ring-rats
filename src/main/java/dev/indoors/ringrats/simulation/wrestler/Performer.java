package dev.indoors.ringrats.simulation.wrestler;

import dev.indoors.ringrats.simulation.condition.Condition;
import dev.indoors.ringrats.simulation.core.Simulatable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.config.Task;

import java.util.Collection;
import java.util.Stack;

@Getter
@Setter
@NoArgsConstructor
public abstract class Performer implements Simulatable {

    int maxEnergy;
    int energy; // current energy
    float staminaCoeff; // rate at which energy depletes

    String name;

    Health health;
    Stack<Task> tasks;
    Collection<Condition> conditions;
}
