package dev.indoors.ringrats.core.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import dev.indoors.ringrats.simulation.match.Match;
import dev.indoors.ringrats.simulation.match.MatchFactory;
import dev.indoors.ringrats.simulation.stipulation.Stipulation;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;

import java.io.IOException;
import java.util.Collection;

public class MatchDeserializer extends JsonDeserializer<Match> {

	@Override
	public Match deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
		throws IOException {

		JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
		JsonNode typeNameNode = rootNode.get("typeName");

		Match match = MatchFactory.getMatch(typeNameNode.asText());

		JsonNode wrestlersNode = rootNode.get("wrestlers");
		if (wrestlersNode != null) {
			ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
			TreeTraversingParser wrestlerFieldsParser = new TreeTraversingParser(wrestlersNode, mapper);
			wrestlerFieldsParser.nextToken();
			Collection<Wrestler> wrestlers = mapper.readValue(wrestlerFieldsParser, new TypeReference<Collection<Wrestler>>() {
			});

			match.setWrestlers(wrestlers);
		}

		JsonNode stipulationNode = rootNode.get("stipulations");
		if (stipulationNode != null) {
			ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
			TreeTraversingParser stipulationFieldsParser = new TreeTraversingParser(stipulationNode, mapper);
			stipulationFieldsParser.nextToken();
			Collection<Stipulation> stipulations = mapper.readValue(stipulationFieldsParser, new TypeReference<Collection<Stipulation>>() {
			});

			match.setStipulations(stipulations);
		}

		return match;
	}
}
