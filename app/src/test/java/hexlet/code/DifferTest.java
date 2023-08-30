package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DifferTest {
    String expect = "";
    String expectRec = "";
    String expectPlain = "";

    @BeforeEach
    public void initExpect() {
        expect = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        expectRec = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        expectPlain = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
                """;
    }

    @Test
    public void testStylishJson() throws Exception {
        String filepath1 = "src/test/resources/Json/file1.json";
        String filepath2 = "src/test/resources/Json/file2.json";
        Parser parser = new Parser(filepath1, filepath2);
        parser.parse();

        var actual = Differ.generate(parser.getData1(), parser.getData2(), "stylish");
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    public void testStylishYml() throws IOException {
        String filepath1 = "src/test/resources/Yml/file1.yml";
        String filepath2 = "src/test/resources/Yml/file2.yml";
        Parser parser = new Parser(filepath1, filepath2);
        parser.parse();

        var actual = Differ.generate(parser.getData1(), parser.getData2(), "stylish");
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    public void testStylishJsonRec() throws IOException {
        String filepath1 = "src/test/resources/Json/file3.json";
        String filepath2 = "src/test/resources/Json/file4.json";
        Parser parser = new Parser(filepath1, filepath2);
        parser.parse();

        var actual = Differ.generate(parser.getData1(), parser.getData2(), "stylish");
        assertThat(actual).isEqualTo(expectRec);
    }

    @Test
    public void testStylishYmlRec() throws IOException {
        String filepath1 = "src/test/resources/Yml/file3.yml";
        String filepath2 = "src/test/resources/Yml/file4.yml";
        Parser parser = new Parser(filepath1, filepath2);
        parser.parse();

        var actual = Differ.generate(parser.getData1(), parser.getData2(), "stylish");
        assertThat(actual).isEqualTo(expectRec);
    }

    @Test
    public void testPlainJson() throws IOException {
        String filepath1 = "src/test/resources/Json/file3.json";
        String filepath2 = "src/test/resources/Json/file4.json";
        Parser parser = new Parser(filepath1, filepath2);
        parser.parse();

        var actual = Differ.generate(parser.getData1(), parser.getData2(), "plain");
        assertThat(actual).isEqualTo(expectPlain);
    }
}
