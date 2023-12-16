package dev.indoors.ringrats.simulation.match;

import dev.indoors.ringrats.simulation.action.ActionResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TurnResult {

	boolean endMatch;
	List<ActionResult> actionResults = new ArrayList<>();

}
