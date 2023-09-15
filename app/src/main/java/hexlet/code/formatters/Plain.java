package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import hexlet.code.exercise.LineDiff;

public final class Plain {
    public static String getFormatter(Map<String, LineDiff> data) {
        StringBuilder result = new StringBuilder();
        for (var item : data.entrySet()) {
            var status = item.getValue().getStatus();
            var value1 = item.getValue().getValue1();
            var value2 = item.getValue().getValue2();
            var key = item.getValue().getKey();
            switch (status) {
                case "changed" -> result.append("Property ")
                        .append(getValue(key))
                        .append(" was updated. From ")
                        .append(getValue(value1))
                        .append(" to ")
                        .append(getValue(value2))
                        .append("\n");
                case "added" -> result.append("Property ")
                        .append(getValue(key))
                        .append(" was added with value: ")
                        .append(getValue(value2))
                        .append("\n");
                case "remove" -> result.append("Property ")
                        .append(getValue(key))
                        .append(" was removed")
                        .append("\n");
                default -> {
                }
            }
        }
        return result.toString().trim();
    }

    private static <T> String getValue(T value) {
        if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return Objects.toString(value);
        }
    }
}
