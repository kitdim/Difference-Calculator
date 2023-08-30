package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;

public class Formatter {
    String format;
    List<String> differ;

    Formatter(List<String> differ, String format) {
        this.differ = differ;
        this.format = format;
    }

    public String getResult() {
        switch (format) {
            case "stylish":
                return Stylish.getFormatter(differ);
            case "plain":
                return Plain.getFormatter(differ);
            default:
                throw new RuntimeException(format + " not supported.");
        }
    }

}
