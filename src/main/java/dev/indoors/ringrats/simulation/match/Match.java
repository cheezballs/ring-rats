package dev.indoors.ringrats.simulation.match;

import dev.indoors.ringrats.simulation.condition.Condition;
import dev.indoors.ringrats.simulation.core.comparator.InitiativeComparator;
import dev.indoors.ringrats.simulation.stipulation.Stipulation;
import dev.indoors.ringrats.simulation.task.FocusAttentionTask;
import dev.indoors.ringrats.simulation.wrestler.Performer;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Getter
@Setter
@Slf4j
public class Match {

	Set<Wrestler> wrestlers;
	Set<Stipulation> stipulations;
	int turnNumber = 0;
	boolean matchActive;

	public Match(Set<Wrestler> wrestlers, Set<Stipulation> stipulations) {
		this.wrestlers = wrestlers;
		this.stipulations = stipulations;
	}

	public void simulateTurn() {
		turnNumber++;
		log.trace("Simulating turn {}.", turnNumber);

		// ordered based on current initiative calculation
		Collection<Wrestler> orderedWrestlers = wrestlers.stream().sorted(new InitiativeComparator()).toList();
		for (Wrestler wrestler : orderedWrestlers) {

		}
	}

	public void start() {
		Set<Condition> startingConditions = getStartingConditions();
		for (Wrestler wrestler : wrestlers) {
			startingConditions.addAll(wrestler.getStartingConditions());
			wrestler.setConditions(startingConditions);
			wrestler.initializeForSimulation();

			// make the wrestler focus on one of the other wrestlers
			// TODO: make this better and smarter, dont hit friendly wrestlers, etc
			Performer performer = findRandomOpponent(wrestler);
			wrestler.addTask(new FocusAttentionTask(performer));
		}
		log.trace("Setting matchActive to true.");
		matchActive = true;
	}

	private Set<Condition> getStartingConditions() {
		log.trace("Processing starting conditions.");
		Set<Condition> startingConditions = new HashSet<>();
		for (Stipulation stipulation : stipulations) {
			log.trace("Adding starting conditions from stipulation {}.", stipulation.getName());
			startingConditions.addAll(stipulation.getStartingConditions());
		}
		log.debug("Processed {} starting conditions.", startingConditions.size());
		return startingConditions;
	}

	public MatchResults end() {
		return new MatchResults();
	}

	private Performer findRandomOpponent(Wrestler wrestler) {
		List<Wrestler> filteredWrestlers = wrestlers.stream().filter(w -> !w.getName().equals(wrestler.getName())).toList();
		Random random = new Random();
		return filteredWrestlers.get(random.nextInt(filteredWrestlers.size()));
	}

}
