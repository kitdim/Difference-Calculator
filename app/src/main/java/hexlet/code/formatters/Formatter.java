package hexlet.code.formatters;

import hexlet.code.LineDiff;

import java.util.Map;

public class Formatter {
    public static String formatting(Map<String, LineDiff> data, String format) {
        return switch (format) {
            case "stylish" -> Stylish.getFormatter(data);
            case "plain" -> Plain.getFormatter(data);
            case "json" -> Json.getFormatter(data);
            default -> throw new RuntimeException(format + " not supported.");
        };
    }

}
