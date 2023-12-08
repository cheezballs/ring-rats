package dev.indoors.ringrats.service;

import dev.indoors.ringrats.service.impl.SimulationServiceImpl;
import dev.indoors.ringrats.simulation.match.MatchResults;
import dev.indoors.ringrats.util.MockMatchConfigurations;
import dev.indoors.ringrats.util.MockWrestlers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(args = {"-f", "examples/1v1-basic.json"})
public class SimulationServiceTests {

	@InjectMocks
	SimulationServiceImpl simService;

	@Test
	void firstTest() {
		MatchResults results = simService.simulateMatch(MockMatchConfigurations.basicOneFall(MockWrestlers.create("Wang Chung", "Dangerous Davey")));
		assertThat(results).isNotNull();
	}
	
}
