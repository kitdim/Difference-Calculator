package hexlet.code.formatters;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Json {

    public static String getFormatter(List<String> differ) {
        StringBuilder result = new StringBuilder("{\n");
        int count = 0;
        for (var item : differ) {
            String[] items = item.split("\\|");
            result.append("  ")
                    .append("\"")
                    .append(items[1])
                    .append("\"")
                    .append(": ")
                    .append(getValue(items[2]))
                    .append(count == differ.size() - 1 ? "\n" : ",\n");
            count++;
        }
        return result.append("}").toString();
    }

    private static String getValue(String value) {
        if (value.charAt(0) == '{' && value.charAt(value.length() - 1) == '}') {
            StringBuilder result = new StringBuilder("{\n");
            String[] words = value.trim()
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
        } else if (value.charAt(0) == '[' && value.charAt(value.length() - 1) == ']') {
            StringBuilder result = new StringBuilder("[");
            String[] words = value.trim()
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
            if (StringUtils.isNumeric(value)
                    || value.equals("null")
                    || value.equals("true")
                    || value.equals("false")) {
                return value;
            } else {
                return "\"" + value + "\"";
            }
        }
    }
}
