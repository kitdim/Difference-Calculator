package hexlet.code.formatters;

import hexlet.code.LineDiff;
import java.util.Map;

public class Stylish {
    public static String getFormatter(Map<String, LineDiff> data) {
        StringBuilder result = new StringBuilder("{\n");
        for (var item: data.entrySet()) {
            var status = item.getValue().getStatus();
            var value1 = item.getValue().getValue1();
            var value2 = item.getValue().getValue2();
            var key = item.getValue().getKey();
            switch (status) {
                    case "changed" -> result.append("  - ")
                            .append(key)
                            .append(": ")
                            .append(value1)
                            .append("\n")
                            .append("  + ")
                            .append(key)
                            .append(": ")
                            .append(value2)
                            .append("\n");
                    case "added" -> result.append("  + ")
                            .append(key)
                            .append(": ")
                            .append(value2)
                            .append("\n");
                    case "remove" -> result.append("  - ")
                            .append(key)
                            .append(": ")
                            .append(value1)
                            .append("\n");
                    case "nothing" -> result.append("    ")
                            .append(key)
                            .append(": ")
                            .append(value1)
                            .append("\n");
                }
        }
        return result.append("}").toString();
    }
}
