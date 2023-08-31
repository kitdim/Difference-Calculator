package hexlet.code.formatters;

import java.util.List;

public class Stylish {
    public static String getFormatter(List<String> diff) {
        StringBuilder result = new StringBuilder("{\n");
        for (var item : diff) {
            String[] items = item.split("\\|");
            String optional = items[0];
            switch (optional) {
                case "+" -> result.append("  + ")
                        .append(items[1])
                        .append(": ")
                        .append(items[2])
                        .append("\n");
                case "-" -> result.append("  - ")
                        .append(items[1])
                        .append(": ")
                        .append(items[2])
                        .append("\n");
                case " " -> result.append("    ")
                        .append(items[1])
                        .append(": ")
                        .append(items[2])
                        .append("\n");
            }
        }
        return result.append("}").toString();
    }
}
