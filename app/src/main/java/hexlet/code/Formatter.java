package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;

public class Formatter {
    public static String formating(List<String> data, String format) {
        switch (format) {
            case "stylish":
                return Stylish.getFormatter(data);
            case "plain":
                return Plain.getFormatter(data);
            case "json":
                return Json.getFormatter(data);
            default:
                throw new RuntimeException(format + " not supported.");
        }
    }

}
