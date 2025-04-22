package sunshare.json.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        if (root == null) {
            return null;
        }

        final var nodes = (ArrayNode) root;
        nodes.add(value);
        writeJson(nodes);

        return fromJsonNode(clazz, value);
    }

    public <T> ArrayList<T> insert(Class<T> clazz, List<T> values) {
        final var root = loadJsonRoot();
        if (root == null) {
            return null;
        }

        final var nodes = (ArrayNode) root;
        final ArrayList<T> insertedObjects = new ArrayList<>();

        for (final T value : values) {
            final JsonNode node = toJsonNode(value);
            insertedObjects.add(value);
            nodes.add(node);
        }
        writeJson(nodes);

        return insertedObjects;
    }

    public <T> ArrayList<T> update(Class<T> clazz, Matcher<T> matcher, Updater<T> updater) {
        final JsonNode root = loadJsonRoot();
        if (root == null) {
            return new ArrayList<>();
        }

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

    public <T> ArrayList<T> delete(Class<T> clazz, Matcher<T> matcher) {
        final JsonNode root = loadJsonRoot();
        if (root == null) {
            return new ArrayList<>();
        }

        final ArrayNode nodes = (ArrayNode) root;
        final ArrayList<T> deletedObjects = new ArrayList<>();

        /*
         * Aqui o loop tem que ser de trás para frente
         * Porque se eu remover o elemnto atual ele vai
         * Para o próximo normalmente, o que não rolaria
         * no loop em sequência
         */
        for (int i = nodes.size() - 1; i >= 0; i--) {
            JsonNode current = nodes.get(i);

            try {
                T object = objectMapper.treeToValue(current, clazz);

                if (matcher.assertion(object)) {
                    deletedObjects.add(object);
                    nodes.remove(i);
                }
            } catch (JsonProcessingException e) {
                System.err.println("Failed to convert node to object: " + e.getMessage());
            }
        }

        writeJson(nodes);
        return deletedObjects;
    }

    public <T> ArrayList<T> select(Class<T> clazz, Matcher<T> matcher) {
        final JsonNode root = loadJsonRoot();
        if (root == null) {
            return new ArrayList<>();
        }

        final ArrayNode nodes = (ArrayNode) root;
        final ArrayList<T> selectedObjects = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            JsonNode current = nodes.get(i);

            try {
                T object = objectMapper.treeToValue(current, clazz);

                if (matcher.assertion(object)) {
                    selectedObjects.add(object);
                }
            } catch (JsonProcessingException e) {
                System.err.println("Failed to convert node to object: " + e.getMessage());
            }
        }

        return selectedObjects;
    }

    private JsonNode loadJsonRoot() {
        try {
            return objectMapper.readTree(jsonFile);
        } catch (IOException e) {
            System.err.println("Failed to load json file: " + e.getMessage());
            return null;
        }
    }

    public JsonNode toJsonNode(Object value) {
        return objectMapper.valueToTree(value);
    }

    public <T> T fromJsonNode(Class<T> clazz, JsonNode node) {
        try {
            return objectMapper.treeToValue(node, clazz);
        } catch (Exception e) {
            System.err.println("Failed to convert node to object: " + e.getMessage());
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
