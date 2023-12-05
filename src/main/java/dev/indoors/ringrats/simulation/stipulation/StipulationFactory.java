package dev.indoors.ringrats.simulation.stipulation;

public class StipulationFactory {

    public static Stipulation getStipulation(String name) {
        switch (name) {
            case "OneFall":
                return new OneFallStipulation();
            default:
                throw new IllegalArgumentException("Unknown stipulation: " + name);
        }
    }
}
