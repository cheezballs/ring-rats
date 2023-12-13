package dev.indoors.ringrats.core.engine;

public class Rand {

	public static int between(int min, int max) {
		if (min > max) {
			throw new IllegalArgumentException("Max must be greater than min");
		}
		return (int) (Math.random() * ((max - min) + 1)) + min;
	}

}
