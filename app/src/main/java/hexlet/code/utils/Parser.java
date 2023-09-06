package hexlet.code.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(Path filepath) throws IOException {
        ObjectMapper objectMapper = factoryMapper(filepath);
        String file = Files.readString(filepath);
        return objectMapper.readValue(file, new TypeReference<>() {
        });
    }

    private static ObjectMapper factoryMapper(Path path) {
        String type = String.valueOf(path).split("\\.")[1];
        switch (type) {
            case "json" -> {
                return new JsonMapper();
            }
            case "yml" -> {
                return new YAMLMapper();
            }
            default -> throw new RuntimeException();
        }
    }
}
