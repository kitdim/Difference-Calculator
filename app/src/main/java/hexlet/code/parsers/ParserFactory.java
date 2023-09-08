package hexlet.code.parsers;

public class ParserFactory {
    public static Parser getParser(String format) {
        switch (format) {
            case "json" -> {
                return new JsonParser();
            }
            case "yml" -> {
                return new YmlParser();
            }
            default -> throw new RuntimeException();
        }
    }
}
