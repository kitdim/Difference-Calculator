package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class DifferTest {

    @Test
    public void test1() throws Exception {
        String filepath1 = "src/test/resources/Json/file1.json";
        String filepath2 = "src/test/resources/Json/file2.json";
        Parser parser = new Parser(filepath1, filepath2);
        parser.parse();

        var actual = Differ.generate(parser.getData1(), parser.getData2());
        String expect = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertThat(expect).isEqualTo(actual);
    }

    @Test
    public void test2() throws IOException {
        String filepath1 = "src/test/resources/Yml/file1.yml";
        String filepath2 = "src/test/resources/Yml/file2.yml";
        Parser parser = new Parser(filepath1, filepath2);
        parser.parse();

        var actual = Differ.generate(parser.getData1(), parser.getData2());
        String expect = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertThat(expect).isEqualTo(actual);
    }
}
