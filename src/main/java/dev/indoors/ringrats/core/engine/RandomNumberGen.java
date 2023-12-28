package dev.indoors.ringrats.core.engine;

import lombok.extern.log4j.Log4j2;

import java.util.Random;

@Log4j2
public class RandomNumberGen {

	private static RandomNumberGen instance;
	private final Random random;

	private RandomNumberGen() {
		random = new Random();
	}

	public static RandomNumberGen getInstance() {
		if (instance == null) {
			instance = new RandomNumberGen();
		}
		return instance;
	}

	public int randomInteger(int max) {
		if (max == 0) {
			return max;
		}

		int gen = (random.nextInt(max));
		log.debug("Generated random number : {}.", gen);
		return gen;
	}

	public int grappleReversalRoll() {
		return randomInteger(100);
	}

	public int pinRoll() {
		return randomInteger(100);
	}

	public float pinSecondsKickout() {
		float gen = random.nextFloat(3);
		if (gen > 2.99f) {
			gen = 2.99f;
		}
		log.debug("Generated a pin count of {} seconds.", gen);
		return gen;
	}

	// used for testing to control the expected output of tests
	public void setSeed(long seed) {
		random.setSeed(seed);
	}

}
