package dev.indoors.ringrats;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.indoors.ringrats.core.RingRats;
import dev.indoors.ringrats.core.exception.ArgumentException;
import dev.indoors.ringrats.simulation.match.Match;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Application {

	private static final String FLAG_DESIGNATOR = "-";
	private static final String INPUT_FILE_FLAG = "f";

	public static void main(String[] args) throws ArgumentException, IOException {
		new RingRats().simulate(buildMatch(args));
	}

	public static Match buildMatch(String[] args) throws ArgumentException {
		String inputFileFlag = FLAG_DESIGNATOR + INPUT_FILE_FLAG;
		String inputFilePath = null;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(inputFileFlag)) {
				inputFilePath = args[i + 1];
				break;
			}
		}

		if (inputFilePath == null) {
			throw new ArgumentException("Invalid argument specified for file input.");
		}

		return parseMatchFromInputFile(inputFilePath);
	}

	private static Match parseMatchFromInputFile(String inputFilePath) throws ArgumentException {
		try {
			URL inputFileUrl = Application.class.getResource(inputFilePath);

			if (inputFileUrl == null) {
				throw new ArgumentException("File not found: " + inputFilePath);
			}

			File inputFile = new File(inputFileUrl.toURI());
			return new ObjectMapper().readValue(inputFile, Match.class);
		} catch (URISyntaxException | IOException e) {
			throw new ArgumentException("Could not build a match from the specified input file.", e);
		}
	}

}

