package hexlet.code.formatters;

import hexlet.code.LineDiff;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatting(Map<String, LineDiff> data, String format) {
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
