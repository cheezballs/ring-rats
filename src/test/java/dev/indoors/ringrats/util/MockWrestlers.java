package dev.indoors.ringrats.util;

import dev.indoors.ringrats.simulation.wrestler.Wrestler;

import java.util.HashSet;
import java.util.Set;

public final class MockWrestlers {

    private MockWrestlers() {
        throw new UnsupportedOperationException("Utility class not intended for instantiation.");
    }

    public static Wrestler create(String name) {
        Wrestler wrestler = new Wrestler();
        wrestler.setName(name);
        return wrestler;
    }

    public static Set<Wrestler> create(String... names) {
        Set<Wrestler> wrestlers = new HashSet<>();
        for (String name : names) {
            wrestlers.add(create(name));
        }
        return wrestlers;
    }

}
