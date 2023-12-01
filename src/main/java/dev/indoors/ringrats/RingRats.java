package dev.indoors.ringrats;

import dev.indoors.ringrats.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RingRats implements CommandLineRunner {

    @Autowired
    ConfigurationService configService;

    public static void main(String[] args) {
        SpringApplication.run(RingRats.class, args);
    }

    @Override
    public void run(String... args) {
        configService.readCommandLineArguments(args);
    }

}

