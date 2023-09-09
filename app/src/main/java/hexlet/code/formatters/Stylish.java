package hexlet.code.formatters;

import hexlet.code.LineDiff;

import java.util.Map;

public class Stylish {
    public static String getFormatter(Map<String, LineDiff> data) {
        StringBuilder result = new StringBuilder("{\n");
        for (var item : data.entrySet()) {
            var status = item.getValue().getStatus();
            var value1 = item.getValue().getValue1();
            var value2 = item.getValue().getValue2();
            var key = item.getValue().getKey();
            switch (status) {
                case "changed" -> {
                    result.append("  - ");
                    result.append(key);
                    result.append(": ");
                    result.append(value1);
                    result.append("\n");
                    result.append("  + ");
                    result.append(key);
                    result.append(": ");
                    result.append(value2);
                    result.append("\n");
                }
                case "added" -> {
                    result.append("  + ");
                    result.append(key);
                    result.append(": ");
                    result.append(value2);
                    result.append("\n");
                }
                case "remove" -> {
                    result.append("  - ");
                    result.append(key);
                    result.append(": ");
                    result.append(value1);
                    result.append("\n");
                }
                case "nothing" -> {
                    result.append("    ");
                    result.append(key);
                    result.append(": ");
                    result.append(value1);
                    result.append("\n");
                }
                default -> throw new RuntimeException();
            }
        }
        return result.append("}").toString();
    }
}
