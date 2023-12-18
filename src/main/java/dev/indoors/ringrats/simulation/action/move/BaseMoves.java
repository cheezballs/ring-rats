package dev.indoors.ringrats.simulation.action.move;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.indoors.ringrats.Application;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@Getter
public class BaseMoves {

	private static BaseMoves instance;
	private final List<OffenseMove> basicGrapples;
	private final List<OffenseMove> basicStrikes;

	private BaseMoves() throws IOException {
		basicGrapples = loadFromConfigs("/config/grapples-basic.json");
		basicStrikes = loadFromConfigs("/config/strikes-basic.json");
	}

	public static synchronized BaseMoves getInstance() throws IOException {
		if (instance == null) {
			instance = new BaseMoves();
		}
		return instance;
	}

	public static OffenseMove findFor(String actionName, String moveName) {
		return switch (actionName) {
			case "grapple" ->
				instance.getBasicGrapples().stream().filter(move -> move.getName().equalsIgnoreCase(moveName)).findFirst().orElse(null);
			case "strike" ->
				instance.getBasicStrikes().stream().filter(move -> move.getName().equalsIgnoreCase(moveName)).findFirst().orElse(null);
			default -> null;
		};
	}

	private List<OffenseMove> loadFromConfigs(String configFilePath) throws IOException {
		try {
			URL inputFileUrl = Application.class.getResource(configFilePath);
			File inputFile = new File(inputFileUrl.toURI());
			return new ObjectMapper().readValue(inputFile, new TypeReference<>() {
			});
		} catch (URISyntaxException e) {
			throw new IOException("Error loading from configs.", e);
		}
	}

}
