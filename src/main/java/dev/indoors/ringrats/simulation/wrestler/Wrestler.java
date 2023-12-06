package dev.indoors.ringrats.simulation.wrestler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Getter
@Setter
public class Wrestler extends Performer {

    int maxEnergy;
    int energy; // current energy
    float staminaCoeff; // rate at which energy depletes
}
