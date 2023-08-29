package hexlet.code;

import java.util.List;

public class Formatter {
    String format;
    List<String> differ;

    Formatter(List<String> differ, String format) {
        this.differ = differ;
        this.format = format;
    }

    public String getFormat() {
        switch (format) {
            case "stylish":
                StringBuilder result = new StringBuilder("{\n");
                for (var item : differ) {
                    result.append(item).append("\n");
                }
                return result.append("}").toString();
            default:
                throw new RuntimeException(format + " not supported.");
        }
    }

}
