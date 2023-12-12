package dev.indoors.ringrats.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.indoors.ringrats.service.impl.SimulationServiceImpl;
import dev.indoors.ringrats.simulation.match.MatchConfiguration;
import dev.indoors.ringrats.simulation.match.MatchResults;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SimulationServiceTests {

	@InjectMocks
	SimulationServiceImpl simService;

	@Autowired
	ObjectMapper mapper;

	@Test
	void firstTest() throws IOException {
		ClassPathResource resource = new ClassPathResource("1v1-basic.json");
		String json = new String(Files.readAllBytes(Paths.get(resource.getURI())));
		MatchConfiguration config = mapper.readValue(json, MatchConfiguration.class);
		MatchResults results = simService.simulateMatch(config);
		assertThat(results).isNotNull();
	}

}
