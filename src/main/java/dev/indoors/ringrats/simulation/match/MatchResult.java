package dev.indoors.ringrats.simulation.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MatchResult {

	List<TurnResult> turnResults = new ArrayList<>();

}
