package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DifferTest {
    String expect = "";
    String expectRec = "";

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
    }

    @Test
    public void test1() throws Exception {
        String filepath1 = "src/test/resources/Json/file1.json";
        String filepath2 = "src/test/resources/Json/file2.json";
        Parser parser = new Parser(filepath1, filepath2);
        parser.parse();

        var actual = Differ.generate(parser.getData1(), parser.getData2(), "stylish");
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    public void test2() throws IOException {
        String filepath1 = "src/test/resources/Yml/file1.yml";
        String filepath2 = "src/test/resources/Yml/file2.yml";
        Parser parser = new Parser(filepath1, filepath2);
        parser.parse();

        var actual = Differ.generate(parser.getData1(), parser.getData2(), "stylish");
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    public void test3() throws IOException {
        String filepath1 = "src/test/resources/Json/file3.json";
        String filepath2 = "src/test/resources/Json/file4.json";
        Parser parser = new Parser(filepath1, filepath2);
        parser.parse();

        var actual = Differ.generate(parser.getData1(), parser.getData2(), "stylish");
        assertThat(actual).isEqualTo(expectRec);
    }

    @Test
    public void test4() throws IOException {
        String filepath1 = "src/test/resources/Yml/file3.yml";
        String filepath2 = "src/test/resources/Yml/file4.yml";
        Parser parser = new Parser(filepath1, filepath2);
        parser.parse();

        var actual = Differ.generate(parser.getData1(), parser.getData2(), "stylish");
        assertThat(actual).isEqualTo(expectRec);
    }
}
