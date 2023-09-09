package hexlet.code.formatters;

import hexlet.code.LineDiff;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

public class Json {

    public static String getFormatter(Map<String, LineDiff> data) {
        StringBuilder result = new StringBuilder("{\n");
        var size = data.entrySet().size();
        var index = 0;
        for (var item : data.entrySet()) {
            var status = item.getValue().getStatus();
            var value1 = item.getValue().getValue1();
            var value2 = item.getValue().getValue2();
            var key = item.getValue().getKey();
            switch (status) {
                case "changed" -> {
                    result.append("  ")
                            .append(formatting(key))
                            .append(": ")
                            .append(formatting(value1));
                    result.append(",\n");
                    result.append("  ")
                            .append(formatting(key))
                            .append(": ")
                            .append(formatting(value2));
                }
                case "added" -> result.append("  ")
                        .append(formatting(key))
                        .append(": ")
                        .append(formatting(value2));
                default -> result.append("  ")
                        .append(formatting(key))
                        .append(": ")
                        .append(formatting(value1));
            }
            index++;
            if (index == size) {
                result.append("\n");
            } else {
                result.append(",\n");
            }
        }
        return result.append("}").toString();
    }

    private static String formatting(Object value) {
        var tempVal = Objects.toString(value);
        if (tempVal.charAt(0) == '{' && tempVal.charAt(tempVal.length() - 1) == '}') {
            StringBuilder result = new StringBuilder("{\n");
            String[] words = tempVal.trim()
                    .replace("{", "")
                    .replace("}", "")
                    .replace("=", ": ")
                    .split(",");
            int count = 0;
            for (var item : words) {
                String[] data = item.trim()
                        .split(":");
                String key = data[0].trim();
                String val = data[1].trim();
                result.append("  ")
                        .append("  \"")
                        .append(key)
                        .append("\"")
                        .append(": ");
                if (StringUtils.isNumeric(val)
                        || val.equals("null")
                        || val.equals("true")
                        || val.equals("false")) {
                    result.append(val);
                } else {
                    result.append("\"")
                            .append(val)
                            .append("\"");
                }
                result.append(count == words.length - 1 ? "\n  }" : ",\n");
                count++;
            }
            return result.toString();
        } else if (tempVal.charAt(0) == '[' && tempVal.charAt(tempVal.length() - 1) == ']') {
            StringBuilder result = new StringBuilder("[");
            String[] words = tempVal.trim()
                    .replace("[", "")
                    .replace("]", "")
                    .split(",");
            int count = 0;
            for (var item : words) {
                item = item.trim();
                if (StringUtils.isNumeric(item)
                        || item.equals("null")
                        || item.equals("true")
                        || item.equals("false")) {
                    result.append(item);
                } else {
                    result.append("\"")
                            .append(item)
                            .append("\"");
                }
                result.append(count == words.length - 1 ? "]" : ", ");
                count++;
            }
            return result.toString();
        } else {
            if (StringUtils.isNumeric(tempVal)
                    || tempVal.equals("null")
                    || tempVal.equals("true")
                    || tempVal.equals("false")) {
                return tempVal;
            } else {
                return "\"" + tempVal + "\"";
            }
        }
    }
}
