package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public final class ParserFactory {
    public static Map<String, Object> getParser(String extension, String content) throws JsonProcessingException {
        switch (extension) {
            case "json" -> {
                return new JsonParser().parse(content);
            }
            case "yml" -> {
                return new YmlParser().parse(content);
            }
            default -> throw new RuntimeException(extension + " - not supported.");
        }
    }
}
