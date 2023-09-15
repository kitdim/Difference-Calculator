package hexlet.code;

import hexlet.code.exercise.DataSupplier;
import hexlet.code.exercise.DifferBuild;
import hexlet.code.formatters.Formatter;

public final class Differ {
    public static String generate(String filepath1, String filepath2, String format) {
        var map1 = DataSupplier.getData(filepath1);
        var map2 = DataSupplier.getData(filepath2);

        var differ = DifferBuild.getDiffer(map1, map2);

        return Formatter.formatting(differ, format);
    }

    public static String generate(String filepath1, String filepath2) {
        return generate(filepath1, filepath2, "stylish");
    }
}
