package hexlet.code.formatters;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Plain {
    public static String getFormatter(List<String> diff) {
        StringBuilder result = new StringBuilder();
        for (var item : diff) {
            String[] items = item.split("\\|");
            String optional = items[items.length - 1];
            switch (optional) {
                case "remove" -> result.append("Property ")
                        .append("'")
                        .append(items[1]).append("'")
                        .append(" was removed")
                        .append("\n");
                case "added" -> result.append("Property ")
                        .append("'")
                        .append(items[1])
                        .append("'")
                        .append(" was added with value: ")
                        .append(getValue(items[2]))
                        .append("\n");
                case "update" -> result.append("Property ")
                        .append("'")
                        .append(items[1])
                        .append("'")
                        .append(" was updated. From ")
                        .append(getValue(items[3]))
                        .append(" to ")
                        .append(getValue(items[2]))
                        .append("\n");
                default -> {
                }
            }
        }
        return result.toString();
    }

    private static String getValue(String value) {
        if (value.charAt(0) == '[' && value.charAt(value.length() - 1) == ']'
                || value.charAt(0) == '{' && value.charAt(value.length() - 1) == '}') {
            return "[complex value]";
        } else if (value.equals("false") || value.equals("true")
                || value.equals("null") || StringUtils.isNumeric(value)) {
            return value;
        } else {
            return "'" + value + "'";
        }
    }
}
