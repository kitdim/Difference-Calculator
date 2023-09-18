package hexlet.code;

import hexlet.code.formatters.Formatter;

public final class Differ {
    public static String generate(String filepath1, String filepath2, String format) {
        var data1 = DataSupplier.getData(filepath1);
        var data2 = DataSupplier.getData(filepath2);

        var differ = DifferBuild.getDiffer(data1, data2);

        return Formatter.formatting(differ, format);
    }

    public static String generate(String filepath1, String filepath2) {
        return generate(filepath1, filepath2, "stylish");
    }
}
