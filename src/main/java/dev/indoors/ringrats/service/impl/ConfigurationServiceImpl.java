package dev.indoors.ringrats.service.impl;

import dev.indoors.ringrats.service.ConfigurationService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    private static final String FLAG_DESIGNATOR = "-";
    private static final String DEBUG_MODE_FLAG = "d";

    @Getter
    @Setter
    private boolean debugMode;

    @Override
    public void readCommandLineArguments(String... args) {
        this.debugMode = isDebugMode(args);
        if (debugMode) {
            for (int a = 0; a < args.length; a++) {
                log.debug("Found argument " + args[a] + " at position " + a);
            }
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
