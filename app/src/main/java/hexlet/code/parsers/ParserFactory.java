package hexlet.code.parsers;

import java.util.Map;

public final class ParserFactory {
    public static Map<String, Object> getParser(String extension, String content) {
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
