package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    private final Path filepath1;
    private final Path filepath2;
    private Map<String, Object> data1;
    private Map<String, Object> data2;

    Parser(String filepath1, String filepath2) {
        this.filepath1 = Paths.get(filepath1).toAbsolutePath().normalize();
        this.filepath2 = Paths.get(filepath2).toAbsolutePath().normalize();
    }

    public void parse() throws IOException {
        ObjectMapper objectMapper = factoryMapper();
        String file1 = Files.readString(this.filepath1);
        String file2 = Files.readString(this.filepath2);
        data1 = objectMapper.readValue(file1, new TypeReference<>() {
        });
        data2 = objectMapper.readValue(file2, new TypeReference<>() {
        });
    }

    private ObjectMapper factoryMapper() throws IOException {
        String type = String.valueOf(filepath1).split("\\.")[1];
        switch (type) {
            case "json" -> {
                return new JsonMapper();
            }
            case "yml" -> {
                return new YAMLMapper();
            }
            default -> throw new IOException();
        }
    }

    public Map<String, Object> getData1() {
        return data1;
    }

    public Map<String, Object> getData2() {
        return data2;
    }
}
