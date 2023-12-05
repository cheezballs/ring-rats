package dev.indoors.ringrats.stipulation;

public class StipulationFactory {

    public static Stipulation getStipulation(String name) {
        switch (name) {
            case "OneFall":
                return new OneFallStipulation();
            default:
                return null;
        }
    }
}
