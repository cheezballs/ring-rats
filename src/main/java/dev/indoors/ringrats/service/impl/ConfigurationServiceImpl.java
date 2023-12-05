package dev.indoors.ringrats.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.indoors.ringrats.exception.ArgumentException;
import dev.indoors.ringrats.match.MatchConfiguration;
import dev.indoors.ringrats.service.ConfigurationService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    private static final String FLAG_DESIGNATOR = "-";
    private static final String DEBUG_MODE_FLAG = "d";
    private static final String INPUT_FILE_FLAG = "f";

    @Getter
    MatchConfiguration matchConfig;

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
        String inputFileFlag = FLAG_DESIGNATOR + DEBUG_MODE_FLAG;
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

        return parseConfigFromInput(inputFilePath);
    }

    private MatchConfiguration parseConfigFromInput(String inputFilePath) throws ArgumentException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(inputFilePath), MatchConfiguration.class);
        } catch (IOException e) {
            throw new ArgumentException("Could not build a match configuration from the specified input file.");
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
