package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DifferTest {
    static String expect = "";
    static String expectRec = "";
    static String expectPlain = "";
    static String expectJson = "";
    static String[] formatters;

    @BeforeAll
    public static void initExpect() throws IOException {
        Path stylishPath1 = Paths.get("src/test/resources/Expect/stylishFile1.txt");
        Path stylishPath2 = Paths.get("src/test/resources/Expect/stylishFile2.txt");
        Path plainPath = Paths.get("src/test/resources/Expect/plainFile1.txt");
        Path jsonPath = Paths.get("src/test/resources/Expect/jsonFile1.txt");
        expect = read(stylishPath1);
        expectRec = read(stylishPath2);
        expectPlain = read(plainPath);
        expectJson = read(jsonPath);
        formatters = new String[]{"stylish", "plain", "json"};
    }

    public static String read(Path filepath) throws IOException {
        return Files.readString(filepath);
    }

    @Test
    public void fullTest() throws IOException {
        String filepath1;
        String filepath2;
        String actual;
        for (var format : formatters) {
            if (format.equals("stylish")) {
                filepath1 = "src/test/resources/Json/file1.json";
                filepath2 = "src/test/resources/Json/file2.json";
                actual = Differ.generate(filepath1, filepath2, "stylish");
                assertThat(actual).isEqualTo(expect);
            } else if (format.equals("plain")) {
                filepath1 = "src/test/resources/Json/file3.json";
                filepath2 = "src/test/resources/Json/file4.json";
                actual = Differ.generate(filepath1, filepath2, "plain");
                assertThat(actual).isEqualTo(expectPlain);
            } else {
                filepath1 = "src/test/resources/Json/file3.json";
                filepath2 = "src/test/resources/Json/file4.json";
                actual = Differ.generate(filepath1, filepath2, "json");
                assertThat(actual).isEqualTo(expectJson);
            }
        }
    }
}
