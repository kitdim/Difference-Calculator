package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DifferTest {
    private static String expectStylish1 = "";
    private static String expectStylish2 = "";
    private static String expectPlain = "";
    private static String expectJson = "";

    @BeforeAll
    public static void initExpect() throws IOException {
        Path stylishPath1 = Paths.get("src/test/resources/Expect/stylishFile1.txt");
        Path stylishPath2 = Paths.get("src/test/resources/Expect/stylishFile2.txt");
        Path plainPath = Paths.get("src/test/resources/Expect/plainFile1.txt");
        Path jsonPath = Paths.get("src/test/resources/Expect/jsonFile1.txt");
        expectStylish1 = read(stylishPath1);
        expectStylish2 = read(stylishPath2);
        expectPlain = read(plainPath);
        expectJson = read(jsonPath);
    }

    public static String read(Path filepath) throws IOException {
        return Files.readString(filepath);
    }
    @Test
    public void stylishTest() throws IOException {
        String filepath1 = "src/test/resources/Json/file1.json";
        String filepath2 = "src/test/resources/Json/file2.json";
        String actual = Differ.generate(filepath1, filepath2, "stylish");
        assertThat(actual).isEqualTo(expectStylish1);
        filepath1 = "src/test/resources/Json/file3.json";
        filepath2 = "src/test/resources/Json/file4.json";
        actual = Differ.generate(filepath1, filepath2, "stylish");
        assertThat(actual).isEqualTo(expectStylish2);
    }
    @Test
    public void plainTest() throws IOException {
        String filepath1 = "src/test/resources/Json/file3.json";
        String filepath2 = "src/test/resources/Json/file4.json";
        String actual = Differ.generate(filepath1, filepath2, "plain");
        assertThat(actual).isEqualTo(expectPlain);
    }
    @Test
    public void jsonTest() throws IOException {
        String filepath1 = "src/test/resources/Json/file3.json";
        String filepath2 = "src/test/resources/Json/file4.json";
        String actual = Differ.generate(filepath1, filepath2, "json");
        assertThat(actual).isEqualTo(expectJson);
    }
    @Test
    public void errorTestPath() {
        String filepath1 = "src/test/resources";
        String filepath2 = "src/test/resources";
        Throwable throwable = assertThrows(Exception.class, () -> {
            Differ.generate(filepath1, filepath2, "json");
        });
        assertNotNull(throwable.getMessage());
    }
    @Test
    public void errorTestFormat() {
        String filepath1 = "src/test/resources/Json/file3.json";
        String filepath2 = "src/test/resources/Json/file4.json";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Differ.generate(filepath1, filepath2, "test");
        });
        String expectedMessage = "test - not supported.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void errorTestExtension() {
        String filepath1 = "src/test/resources/Json/file3.img";
        String filepath2 = "src/test/resources/Json/file4.img";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Differ.generate(filepath1, filepath2, "test");
        });
        String expectedMessage = "img - not supported.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
