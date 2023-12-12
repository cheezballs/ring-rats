package dev.indoors.ringrats.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.indoors.ringrats.simulation.match.MatchConfiguration;
import dev.indoors.ringrats.simulation.stipulation.Stipulation;
import dev.indoors.ringrats.simulation.stipulation.StipulationFactory;
import dev.indoors.ringrats.simulation.wrestler.Wrestler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MatchConfigDeserializer extends JsonDeserializer<MatchConfiguration> {

	@Override
	public MatchConfiguration deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
		throws IOException {
		JsonNode root = jsonParser.getCodec().readTree(jsonParser);
		ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();

		Set<Wrestler> wrestlers = mapper.treeToValue(root.get("wrestlers"), mapper.getTypeFactory().constructCollectionType(HashSet.class, Wrestler.class));

		Set<Stipulation> stipulations = new HashSet<>();
		for (JsonNode stipNode : root.get("stipulations")) {
			Stipulation stipulation = StipulationFactory.getStipulation(stipNode.get("name").asText());
			stipulations.add(stipulation);
		}

		return new MatchConfiguration(stipulations, wrestlers);
	}
}
