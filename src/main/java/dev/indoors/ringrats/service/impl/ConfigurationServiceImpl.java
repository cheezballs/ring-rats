package dev.indoors.ringrats.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.indoors.ringrats.service.ConfigurationService;
import dev.indoors.ringrats.simulation.core.exception.ArgumentException;
import dev.indoors.ringrats.simulation.match.MatchConfiguration;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

@Slf4j
@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	private static final String FLAG_DESIGNATOR = "-";
	private static final String DEBUG_MODE_FLAG = "d";
	private static final String INPUT_FILE_FLAG = "f";

	@Autowired
	ObjectMapper objectMapper;

	@Getter
	private boolean debugMode;

	@Override
	public void readCommandLineArguments(String... args) {
		this.debugMode = isDebugMode(args);
		for (int a = 0; a < args.length; a++) {
			log.debug("Found argument {} at position {}.", args[a], a);
		}
	}

	@Override
	public MatchConfiguration buildMatchConfiguration(String[] args) throws ArgumentException {
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

		return parseConfigFromInputFile(inputFilePath);
	}

	private MatchConfiguration parseConfigFromInputFile(String inputFilePath) throws ArgumentException {
		try {
			URL inputFileUrl = ResourceLoader.class.getClassLoader().getResource(inputFilePath);

			if (inputFileUrl == null) {
				throw new ArgumentException("File not found: " + inputFilePath);
			}

			File inputFile = new File(inputFileUrl.toURI());
			return objectMapper.readValue(inputFile, MatchConfiguration.class);
		} catch (URISyntaxException | IOException e) {
			throw new ArgumentException("Could not build a match configuration from the specified input file.", e);
		}
	}

	private boolean isDebugMode(String[] args) {
		String debugModeFlag = FLAG_DESIGNATOR + DEBUG_MODE_FLAG;
		for (String arg : args) {
			if (arg.equals(debugModeFlag)) {
				return true;
			}
		}
		return false;
	}
}
