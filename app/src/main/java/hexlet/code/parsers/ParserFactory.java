package hexlet.code.parsers;

public final class ParserFactory {
    public static Parser getParser(String extension) {
        switch (extension) {
            case "json" -> {
                return new JsonParser();
            }
            case "yml" -> {
                return new YmlParser();
            }
            default -> throw new RuntimeException(extension + " - not supported.");
        }
    }
}
