package sunshare.json_manager;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JsonManager {

    public final ObjectMapper objectMapper;
    private final File jsonFile;

    public JsonManager(final String file) {
        jsonFile = new File(file);
        objectMapper = new ObjectMapper();
    }

    public void insert(final JsonNode value) {
        final var root = loadJsonRoot();
        final var nodes = (ArrayNode) root;

        nodes.add(value);

        writeJson(nodes);
    }

    public void update() {
    }

    public void select() {
    }

    public void delete() {
    }

    private JsonNode loadJsonRoot() {
        try {
            return objectMapper.readTree(jsonFile);
        } catch (IOException e) {
            System.err.println("Failed to load json file");
            return null;
        }
    }

    private void writeJson(JsonNode node) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, node);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to write the json file");
        }
    }
}
