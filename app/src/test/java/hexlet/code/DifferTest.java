package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class DifferTest {

    @Test
    public void test1() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";

        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String json1 = Files.readString(path1);
        String json2 = Files.readString(path2);
        Map<String, Object> data1 = objectMapper.readValue(json1, new TypeReference<>() {
        });
        Map<String, Object> data2 = objectMapper.readValue(json2, new TypeReference<>() {
        });
        var actual = Differ.generate(data1, data2);
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
