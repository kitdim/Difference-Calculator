package hexlet.code;


import hexlet.code.utils.Formatter;
import hexlet.code.utils.Parser;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        var data1 = Parser.parse(path1);
        var data2 = Parser.parse(path2);
        var differ = DifferBuild.getDiffer(data1, data2);

        return Formatter.formatting(differ, format);
    }
}
