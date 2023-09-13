package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import hexlet.code.formatters.Formatter;
import hexlet.code.parsers.ParserFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        var extension = filepath1.split("\\.")[1];
        var path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        var path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        var file1 = ParserFactory
                .getParser(extension)
                .parse(path1);
        var file2 = ParserFactory
                .getParser(extension)
                .parse(path2);

        var data1 = Files.readString(path1);
        var data2 = Files.readString(path2);
        var map1 = file1.readValue(data1, new TypeReference<Map<String, Object>>() {
        });
        var map2 = file2.readValue(data2, new TypeReference<Map<String, Object>>() {
        });

        var differ = DifferBuild.getDiffer(map1, map2);

        return Formatter.formatting(differ, format);
    }
    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }
}
