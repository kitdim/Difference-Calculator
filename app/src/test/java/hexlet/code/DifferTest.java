package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import hexlet.code.utils.Differ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DifferTest {
    String expect = "";
    String expectRec = "";
    String expectPlain = "";
    String expectJson = "";
    String[] formatters;

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
        expectJson = """
                {
                  "chars1": ["a", "b", "c"],
                  "chars2": ["d", "e", "f"],
                  "chars2": false,
                  "checked": false,
                  "checked": true,
                  "default": null,
                  "default": ["value1", "value2"],
                  "id": 45,
                  "id": null,
                  "key1": "value1",
                  "key2": "value2",
                  "numbers1": [1, 2, 3, 4],
                  "numbers2": [2, 3, 4, 5],
                  "numbers2": [22, 33, 44, 55],
                  "numbers3": [3, 4, 5],
                  "numbers4": [4, 5, 6],
                  "obj1": {
                    "nestedKey": "value",
                    "isNested": true
                  },
                  "setting1": "Some value",
                  "setting1": "Another value",
                  "setting2": 200,
                  "setting2": 300,
                  "setting3": true,
                  "setting3": "none"
                }""";
        formatters = new String[]{"stylish", "plain", "json"};
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
