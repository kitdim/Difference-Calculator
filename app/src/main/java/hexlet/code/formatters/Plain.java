package hexlet.code.formatters;

import java.util.Map;
import java.util.Objects;

import hexlet.code.LineDiff;
import org.apache.commons.lang3.StringUtils;

public class Plain {
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

    private static String getValue(Object value) {
        var tempVal = Objects.toString(value);
        if (tempVal.charAt(0) == '[' && tempVal.charAt(tempVal.length() - 1) == ']'
                || tempVal.charAt(0) == '{' && tempVal.charAt(tempVal.length() - 1) == '}') {
            return "[complex value]";
        } else if (tempVal.equals("false") || tempVal.equals("true")
                || tempVal.equals("null") || StringUtils.isNumeric(tempVal)) {
            return tempVal;
        } else {
            return "'" + value + "'";
        }
    }
}
