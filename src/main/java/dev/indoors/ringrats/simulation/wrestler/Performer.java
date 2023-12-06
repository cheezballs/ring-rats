package dev.indoors.ringrats.simulation.wrestler;

import dev.indoors.ringrats.simulation.condition.Condition;
import dev.indoors.ringrats.simulation.core.Simulatable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.config.Task;

import java.util.Set;
import java.util.Stack;

@Getter
@Setter
@NoArgsConstructor
public abstract class Performer implements Simulatable {

    String name;

    Health health;
    Stack<Task> tasks;
    Set<Condition> conditions;
    Set<Condition> startingConditions;
}
