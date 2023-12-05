package dev.indoors.ringrats.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import dev.indoors.ringrats.simulation.stipulation.Stipulation;
import dev.indoors.ringrats.simulation.stipulation.StipulationFactory;

import java.io.IOException;

public class StipulationDeserializer extends JsonDeserializer<Stipulation> {

    @Override
    public Stipulation deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String name = node.get("name").asText();

        return StipulationFactory.getStipulation(name);
    }
}
