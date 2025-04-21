package sunshare.json.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JsonManager {

    public final ObjectMapper objectMapper;
    private final File jsonFile;

    public JsonManager(final String file) {
        jsonFile = new File(file);
        objectMapper = new ObjectMapper();
    }

    public <T> T insert(Class<T> clazz, JsonNode value) {
        final var root = loadJsonRoot();
        final var nodes = (ArrayNode) root;

        nodes.add(value);

        writeJson(nodes);

        try {
            return objectMapper.treeToValue(value, clazz);
        } catch (JsonProcessingException e) {
            System.err.println("Failed to return the inserted value");
            return null;
        }
    }

    public <T> ArrayList<T> update(Class<T> clazz, Matcher<T> matcher, Updater<T> updater) {
        final JsonNode root = loadJsonRoot();
        final ArrayNode nodes = (ArrayNode) root;
        final ArrayList<T> updatedObjects = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            JsonNode current = nodes.get(i);

            try {
                T object = objectMapper.treeToValue(current, clazz);

                if (matcher.assertion(object)) {
                    final T updatedObject = updater.update(object);
                    final JsonNode updatedNode = objectMapper.valueToTree(updatedObject);
                    updatedObjects.add(updatedObject);
                    nodes.set(i, updatedNode);
                }
            } catch (JsonProcessingException e) {
                System.err.println("Failed to convert node to object: " + e.getMessage());
            }
        }

        writeJson(nodes);
        return updatedObjects;
    }

    public <T> void delete(Class<T> clazz, Matcher<T> matcher) {
    }

    public <T> ArrayList<T> select(Class<T> clazz, Matcher<T> matcher) {
        return null;
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
