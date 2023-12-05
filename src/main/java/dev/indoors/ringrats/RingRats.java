package dev.indoors.ringrats;

import dev.indoors.ringrats.exception.ArgumentException;
import dev.indoors.ringrats.match.MatchConfiguration;
import dev.indoors.ringrats.service.ConfigurationService;
import dev.indoors.ringrats.service.SimulationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class RingRats implements CommandLineRunner {

    @Autowired
    ConfigurationService configService;

    @Autowired
    SimulationService simulationService;

    public static void main(String[] args) {
        SpringApplication.run(RingRats.class, args);
    }

    @Override
    public void run(String... args) {
        configService.readCommandLineArguments(args);
        try {
            MatchConfiguration matchConfig = configService.buildMatchConfiguration(args);
            simulationService.simulateMatch(matchConfig);
        } catch (ArgumentException e) {
            log.error(e.getMessage(), e);
        }

    }

}

