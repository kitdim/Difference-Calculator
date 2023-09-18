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
    private static String expectStylish = "";
    private static String expectPlain = "";
    private static String expectJson = "";

    @BeforeAll
    public static void initExpect() throws IOException {
        expectStylish = read("src/test/resources/Expect/stylishFile2.txt");
        expectPlain = read("src/test/resources/Expect/plainFile1.txt");
        expectJson = read("src/test/resources/Expect/jsonFile1.txt");
    }

    public static String read(String filepath) throws IOException {
        Path path = Paths.get(filepath);
        return Files.readString(path);
    }

    @Test
    public void stylishTest() throws IOException {

        String filepath1 = "src/test/resources/Json/file3.json";
        String filepath2 = "src/test/resources/Json/file4.json";
        String actual = Differ.generate(filepath1, filepath2, "stylish");
        assertThat(actual).isEqualTo(expectStylish);
        actual = Differ.generate(filepath1, filepath2);
        assertThat(actual).isEqualTo(expectStylish);
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
        String filepath1 = "src/test/resources/Other/file1.img";
        String filepath2 = "src/test/resources/Other/file2.img";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Differ.generate(filepath1, filepath2, "stylish");
        });
        String expectedMessage = "img - not supported.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
