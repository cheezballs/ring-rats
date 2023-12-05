package dev.indoors.ringrats.simulation.stipulation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OneFallStipulation implements Stipulation {

    @Override
    public String getName() {
        return "One Fall";
    }
}
