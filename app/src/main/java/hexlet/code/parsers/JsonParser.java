package hexlet.code.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.nio.file.Path;

public class JsonParser implements Parser {
    @Override
    public ObjectMapper parse(Path path) {
        return new JsonMapper();
    }
}
