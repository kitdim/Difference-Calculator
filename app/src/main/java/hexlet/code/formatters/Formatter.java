package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.exercise.LineDiff;

import java.util.Map;

public final class Formatter {
    public static String formatting(Map<String, LineDiff> data, String format) throws JsonProcessingException {
        return switch (format) {
            case "stylish" -> Stylish.getFormatter(data);
            case "plain" -> Plain.getFormatter(data);
            case "json" -> Json.getFormatter(data);
            default -> throw new RuntimeException(format + " - not supported.");
        };
    }

}
