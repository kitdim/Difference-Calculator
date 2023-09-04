package hexlet.code.utils;


import java.io.IOException;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Parser parser = new Parser(filepath1, filepath2);
        parser.parse();
        var data1 = parser.getData1();
        var data2 = parser.getData2();
        var differ = DifferBuild.getDiffer(data1, data2);

        return Formatter.formatting(differ, format);
    }
}
