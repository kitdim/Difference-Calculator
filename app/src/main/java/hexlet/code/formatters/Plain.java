package hexlet.code.formatters;

import java.util.List;

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
                        .append(items[2])
                        .append("\n");
                case "update" -> result.append("Property ")
                        .append("'")
                        .append(items[1])
                        .append("'")
                        .append(" was updated. From ")
                        .append(items[3])
                        .append(" to ")
                        .append(items[2])
                        .append("\n");
            }
        }
        return result.toString();
    }
    public static String getValue(String value) {
        return null;
    }
}
